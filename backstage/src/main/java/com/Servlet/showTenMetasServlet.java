package com.Servlet;

import com.Entity.filemetas;
import com.Entity.filemetas2;
import com.Service.ServiceImpl.showTenMetasServiceImpl;
import com.Service.showTenMetasService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
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
 * @create 2019-11-03 19:23
 */
@WebServlet("/showTenMetasServlet")
public class showTenMetasServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        int pagenum = 10;
        int pagesize = 10;
        showTenMetasService showTenMetasService = new showTenMetasServiceImpl();
        List<filemetas2> filemetas;
        try {
            filemetas = showTenMetasService.GetlastTenMetas(pagenum, pagesize);
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(filemetas);
            System.out.println(s);
            response.getWriter().append(s);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
