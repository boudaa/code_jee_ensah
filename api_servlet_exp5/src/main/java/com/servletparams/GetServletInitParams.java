package com.servletparams;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/", initParams = {
		@WebInitParam(name = "param1", value = "Je suis un paramètre1"),
		@WebInitParam(name = "param2", value = "Je suis un paramètre2") })
public class GetServletInitParams extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String param1 = getServletConfig().getInitParameter("param1");
		System.out.println(param1);

		String param2 = getServletConfig().getInitParameter("param2");
		System.out.println(param2);

		// On spécifie un type pour la réponse, et l'encodage
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<p>" + param1 + "</p>");
		out.println("<p>" + param2 + "</p>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
