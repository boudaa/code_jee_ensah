package com.contextparams;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class GetContextParamsInServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On récupère le context
		ServletContext cntx = getServletContext();

		// On récupère le paramètre du context portant le nom type_db
		String contextParam = cntx.getInitParameter("type_db");

		PrintWriter out = response.getWriter();

		out.println(contextParam);

	}


}
