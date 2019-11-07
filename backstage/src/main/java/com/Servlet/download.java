package com.Servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-02 23:16
 */
@WebServlet("/download")
public class download extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        System.out.println(path+"下载路径path");
        response.addHeader("Content-Disposition", "attachment;filename="
                + path);
        InputStream inputStream=new FileInputStream(path);
        ServletOutputStream outputStream = response.getOutputStream();
        byte b[]=new byte[1024];
        int n=-1;
        while((n=inputStream.read(b))!=-1){
            outputStream.write(b);
        }
        outputStream.close();
        inputStream.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
