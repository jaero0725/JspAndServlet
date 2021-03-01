package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");
	      String id = request.getParameter("id");
	      String pwd = request.getParameter("pwd");
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "mytest", "mytest");
	         pstmt = conn.prepareStatement("select pass from login where id = ?");
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	            String dbPass = rs.getString("pass");
	            if(dbPass.equals(pwd)) {
	               HttpSession session = request.getSession();
	               // 클라이언트의 정보를 HttpSession객체에 저장
	               session.setAttribute("user", id);
	            }else {
	               System.err.println("비밀번호가 틀렸습니다.");
	            }
	         }else {
	            System.err.println("아이디가 존재하지 않습니다.");
	         }
	      }catch(ClassNotFoundException cnfe) {
	         System.err.println("드라이버 로딩 실패");
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	         System.err.println("쿼리문 오류");
	      }finally {
	         try { if(rs != null) rs.close(); }catch(SQLException sqle) { }
	         try { if(pstmt != null) pstmt.close(); }catch(SQLException sqle) { }
	         try { if(conn != null) conn.close(); }catch(SQLException sqle) { }
	      }
	      response.sendRedirect("Login");
	   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
