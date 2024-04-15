package com.ensah;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/")
public class GetRequestParamExampleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        //get the request parameter named nom
		String name = request.getParameter("nom");
		String msg;
		if(name==null) {
			msg ="Paramètre nom absent";
		}
		else {
			msg ="le nom est : "+name;
		}

		//put the value of the parameter in the response
		//it will be displayed by the browser
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(msg);

	}


}
