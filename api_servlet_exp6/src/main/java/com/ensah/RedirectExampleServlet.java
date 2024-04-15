package com.ensah;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/")
public class RedirectExampleServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// On récupère un paramètre de la reqûete qui indique la tâche demandée,
		// ce paramètre attendu est : task
		String param = request.getParameter("task");

		// Suite à la valeur de ce paramère on execute l'une des redirections suivantes:

		if ("server".equals(param)) {		
		//redirection coté serveur
		request.getServletContext().getRequestDispatcher("/test/target").forward(request, response);
	
		}
		else if ("client".equals(param)){
			//coté client
			response.sendRedirect("http://www.ensah.ma");

		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("il faut appeler cette servlet avec un paramètre task qui prend soit client ou server comme valeur");

		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
