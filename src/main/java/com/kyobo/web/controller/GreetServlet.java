package com.kyobo.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/greet")
public class GreetServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
		resp.setContentType("text/html; charset=UTF-8");
		String err = """
			<style>
			    h1 {
			        text-align: center;
			    }
			</style>
			<br><br><br><br><br><br><br><br><br><br><br><br><br>
			<h1>
			쿼리로 들어오다니!!!<br>
			히히 오줌 발싸!
			</h1>
			""";

		String name = req.getParameter("name");
		String email = req.getParameter("email");

		if (name == null || name.isBlank()) {
			resp.getWriter().println(err);
		} else {
			if (email == null || email.isBlank()) {
				email = new String("tkdwls0619@hufs.ac.kr");
			}
			resp.getWriter().printf("""
				<br><br><br><br><br><br><br><br><br><br><br>
				<fieldset style="width: 80%%; text-align: center; ">
				    <legend><h1>당신의 상세 정보</h1><br></legend>
				    <h2>이름: %s</h2><br>
				    <h2>메일: %s</h2>
				</fieldset>
				""", name, email);
		}
	}


}
