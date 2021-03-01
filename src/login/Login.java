package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		try{
			HttpSession session = request.getSession(false);
			//세션이 있는 경우
			if(session != null) {
				String sessionId = session.getId();
				System.out.println("세션 아이디 : " + sessionId);
				String user = (String) session.getAttribute("user");
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head><title>InitParam Servlet</title></head><body>");
				out.println("<form method='post' action='LoginCheck'>");
				out.println("<table border='1' width='300'");
				
				out.println("<tr>");
				out.println("<th width =300 align=center>" + user +" 님 로그인 되었습니다.</td></tr>");
				
				out.println("<tr><td align=center>");
				out.println("<a href=#>회원정보</a>"
						+ "<a href=Logout>로그아웃</a></td></tr></form></table></body></html>");
			}
			//세션이 없는 경우
			else {
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head><title>InitParam Servlet</title></head><body>");
				out.println("<form method='post' action='LoginCheck'>");
				out.println("<table border='1' width='300'");
				
				out.println("<tr>");
				out.println("<th width =100> 아이디 </th>");
				out.println("<td width =200>&nbsp;"
						+ "<input type=text name=id></td></tr>");
				
				out.println("<th width =100> 비밀번호 </th>");
				out.println("<td width =200>&nbsp;"
						+ "<input type=password name=pwd></td></tr>");
				
				out.println("<tr><td align=center colspan=2>");
				out.println("<input type=button value=회원가입>");
				out.println("<input type=submit value=로그인></td></tr></form></table></body></html>");
			}
		
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
