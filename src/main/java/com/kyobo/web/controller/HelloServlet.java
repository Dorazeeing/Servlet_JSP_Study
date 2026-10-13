package com.kyobo.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        String brSet="<br><br><br><br><br><br><br><br><br><br><br><br>";
        String mqCss="'font-size: 45px'";
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("name");

        if(name == null || name.isEmpty()){
            name = new String("Servlet");
            resp.getWriter().println(brSet + "<marquee style=" + mqCss + ">잘못된 이름!!!</marquee>");
        }
        else {
            resp.getWriter().println(brSet + "<marquee style="+mqCss+">요청 성공!!</marquee>");
            resp.getWriter().println("<br>");
            resp.getWriter().println("<h2 style='text-align:center'>안녕하세요. "+ name +" 님! </h2>");
            resp.getWriter().println("<br>");
            resp.getWriter().println("<marquee style='font-size: 45px'>요청 성공!!</marquee>");
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("charset=UTF-8");
        this.doGet(req, resp);
    }
}
