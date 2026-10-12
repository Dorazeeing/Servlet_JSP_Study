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
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("name");
        if(name == null || name.isBlank()) {
            String style = "font-size: 45px";
            String brSet = "<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>";
            resp.getWriter().println(brSet +"<marquee style=\""+ style +"\">잘못된 정보입니다.</marquee>");
        }
        else {
            resp.getWriter().println("<h1>요청 정상</h1>");
            resp.getWriter().println("<hr>");
            resp.getWriter().println("<br>");

            resp.getWriter().println("<p style='font-size: 17.5px'>");
            resp.getWriter().println("안녕하세요, " + name + "님 </p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        this.doGet(req, resp);
    }
}