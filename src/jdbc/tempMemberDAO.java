package jdbc;

import java.sql.*;
import java.util.Vector;

public class tempMemberDAO {
	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "mytest";
	private final String PASS = "mytest";

	public tempMemberDAO() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (Exception e) {
			System.out.println("jdbc 드라이버 로딩 실패");
		}
	}

	public Vector<TempMemberVO> getMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<TempMemberVO> vecList = new Vector<>();

		try {
			conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from tempmember");
			while (rs.next()) {
				TempMemberVO vo = new TempMemberVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1(rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2"));
				vo.setEmail(rs.getString("e_mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress(rs.getString("address"));
				vo.setJob(rs.getString("job"));
				vecList.add(vo);
			}
		} catch (Exception e) {
			System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(stmt != null) try{ stmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return vecList;
	}
	
}
