package sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String company;
	private String manager;
	private String tel;
	private String email;
	
	@Override
	public void init() throws ServletException {
		System.out.println("초기화 메서드 실행");
		company = getServletContext().getInitParameter("company");
		manager = getServletContext().getInitParameter("manager");
		tel = getServletConfig().getInitParameter("tel");
		email = getServletConfig().getInitParameter("email");
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try{
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>InitParam Servlet</title></head><body>");
			out.println("<h2>InitParam Servlet</h2>");
			out.println("<br><hr color='red'><br>");
			out.println("<li> 회사명 : " + company +"</li>");
			out.println("<li> 담당자명 : " + manager +"</li>");
			out.println("<li> 전화번호 : " + tel +"</li>");
			out.println("<li> 이메일 : " + email +"</li>");
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
