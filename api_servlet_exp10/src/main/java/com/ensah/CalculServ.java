package com.ensah;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/")
public class CalculServ extends HttpServlet {



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//check if a request param exists in the request		
		if(request.getParameter("form")!=null) {
			//in this case this servlet (the controller) redirects to the form jsp
			request.getRequestDispatcher("WEB-INF/pages/form.jsp").forward(request, response);
			return;
		}
	
		//check if the values of x , y and the operation are in the request params
		else if(request.getParameter("x") !=null && request.getParameter("y") !=null && request.getParameter("operation") !=null) {
			
			//get values of x and y as double
			double x =	 Double.parseDouble(request.getParameter("x"));
			double y =  Double.parseDouble(request.getParameter("y"));
			//if it is an addition
			if("+".equals(request.getParameter("operation"))){
				request.setAttribute("msg",x+y);
			}
			//if it is a division
			if("/".equals(request.getParameter("operation"))){
				//check the case of 0
				if(y==0) {
					request.setAttribute("msg","division by 0");
				}
				else {
					request.setAttribute("msg",x/y);
				}
			}
			//if it is a multiplication
			if("*".equals(request.getParameter("operation"))){
				request.setAttribute("msg",x*y);
			}
			
			if("-".equals(request.getParameter("operation"))){
				request.setAttribute("msg",x-y);
			}
			//redirect to the result JSP
			request.getRequestDispatcher("WEB-INF/pages/result.jsp").forward(request, response);
			return;
		}
		else {
			//redirect to the form JSP
			request.getRequestDispatcher("WEB-INF/pages/form.jsp").forward(request, response);
			return;
		}
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
