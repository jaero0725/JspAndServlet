package sample;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try{
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>My first Servlet</title></head><body>");
			out.println("<h2>my first Servlet file</h2>");
			out.println("<br><hr color='red'><br>");
			out.println("<div align='center'>");
			out.println(new java.util.Date());
			out.println("</div></body></html>");
		} finally{
			out.close();
		}
	}
}