package com.Servlet;

import com.Entity.filemetas;
import com.Entity.pathstatus;
import com.Service.ServiceImpl.UploadAndDownloadServiceServiceImpl;
import com.Utils.Options;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.text.html.Option;
import java.io.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author ncc
 * 负责上传的servlet
 * 根据年月日时分秒生成对应文件夹，后将文件存入其中。
 */
@WebServlet("/upload")
@MultipartConfig
public class upload extends HttpServlet {
    //设置盘符
    static String basepath = Options.BASES_PATH;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        Part part = request.getPart("file");
        String fileName = request.getParameter("name");
        System.out.println(fileName+"通过formdata获取这个数据");
        //返回元数据信息
        filemetas filemeta = getmeta(fileName, part);
        if (filemeta != null) {
            //对元数据信息进行上传
            UploadAndDownloadServiceServiceImpl UploadAndDownloadServiceImpl = new UploadAndDownloadServiceServiceImpl();
            Integer u = 0;
            try {
                u = UploadAndDownloadServiceImpl.upload(filemeta);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //对象转json，并返回给客户端json格式的元数据信息
            String s = getjson(filemeta);
            if (u > 0) {
                response.getWriter().append(s);
            } else {
                response.getWriter().append("数据返回错误");
            }
        }


    }


    //    上传文件   存储的盘符路径在这里设置
    public pathstatus uploadfile(Part part, String filesavepath) throws IOException {
        pathstatus pathstatus = new pathstatus();
        Calendar now = Calendar.getInstance();
        String year = now.get(Calendar.YEAR) + "";
        String month = (now.get(Calendar.MONTH) + 1) + "";
        String day = now.get(Calendar.DAY_OF_MONTH) + "";
        String hour = now.get(Calendar.HOUR_OF_DAY) + "";
        String minute = now.get(Calendar.MINUTE) + "";
        String second = now.get(Calendar.SECOND) + "";
        String directorypath = year + month + day + hour + minute + second;
        File file = new File(basepath + directorypath);
        String path = basepath + directorypath + "\\" + filesavepath;
        if (!file.exists()) {
            file.mkdir();
            InputStream inputStream = part.getInputStream();
            OutputStream outputStream = new FileOutputStream(new File(path));
            int n = -1;
            byte b[] = new byte[1024];
            while ((n = inputStream.read(b)) != -1) {
                outputStream.write(b);
            }
            outputStream.close();
            inputStream.close();
            pathstatus.setFlag(true);
            pathstatus.setPath(path);
            return pathstatus;
        }
        pathstatus.setFlag(false);
        pathstatus.setPath(null);
        return pathstatus;

    }

    //    根据文件信息获取元数据信息
    public filemetas getmeta(String fileName, Part part) throws IOException {
        int pos = fileName.lastIndexOf(".");
        String fileType = fileName.substring(pos + 1);        // 文件类型
        String fileoldname = fileName.substring(0, pos);      //原始文件名
        String uuidName = UUID.randomUUID().toString();                     //新名字
        String filesavepath = uuidName + "." + fileType;

        pathstatus uploadfile = uploadfile(part, filesavepath);
        long size = part.getSize();                             //文件大小
        String Stringsize = new String(String.valueOf(size));
        Date time = new Date();                                 //创建时间
        //保存到数据库
        //文件大小，文件类型，原始文件名，创建时间，文件保存地址
        System.out.println(fileType + ">>" + fileoldname + ">>" + filesavepath + ">>" + size + ">>" + time);
        filemetas filemetas = new filemetas(Stringsize, fileType, fileoldname, time, uploadfile.getPath());
        if (uploadfile.getFlag()) {
            return filemetas;
        }
        return null;

    }

    //    对象转json
    public String getjson(filemetas filemeta) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(filemeta);
        return s;
    }

}
