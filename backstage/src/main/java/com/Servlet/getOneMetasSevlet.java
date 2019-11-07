package com.Servlet;

import com.Entity.filemetas;
import com.Entity.filemetas2;
import com.Service.ServiceImpl.getOneMetaServiceImpl;
import com.Service.ServiceImpl.selectAllServiceimpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-04 1:25
 */
@WebServlet("/getOneMeta")
@MultipartConfig
public class getOneMetasSevlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        String name = req.getParameter("name");
        System.out.println(uuid);
        System.out.println(name);
        if(uuid!=null){
            selectAllServiceimpl selectAllServiceimpl=new selectAllServiceimpl();
            List<filemetas2> filemetas2s = null;
            try {
                filemetas2s = selectAllServiceimpl.selectAlls();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <filemetas2s.size() ; i++) {
                String filesavepath = filemetas2s.get(i).getFilesavepath();
                int pos1 = filesavepath.lastIndexOf("\\");
                int pos2 = filesavepath.lastIndexOf(".");
//                System.out.println((pos1+1)+"   "+pos2);
                String substring = filesavepath.substring(pos1+1, pos2);
                System.out.println(substring + "<<<<<<");
                if(substring.equals(uuid)){
                    System.out.println("皮配成功");
                    ObjectMapper objectMapper=new ObjectMapper();
                    String s = objectMapper.writeValueAsString(filemetas2s.get(i));
                    resp.getWriter().append(s);
                    break;
                }
            }
        }
    }
    
}
