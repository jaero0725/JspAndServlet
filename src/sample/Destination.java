package sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Destination")
public class Destination extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try{
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>InitParam Servlet</title></head><body>");
			out.println("<h2>Destianation Servlet 입니다. </h2>");
			out.println("<h2>"+ request.getAttribute("myName") +" </h2>");
			String myAge= (String) request.getAttribute("myAge");
			out.println("<h2>"+myAge+"</h2>");
			out.println("</body></html>");
		} finally{
			out.close();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
