package jdbc;

import java.sql.*;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TempMemberDBCP {

	public TempMemberDBCP() {}
	private Connection getConnection() {
		Connection conn =null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");  //web.xml 에서 보고 가져옴.
			conn = ds.getConnection();
		} catch(NamingException ne) {
			ne.printStackTrace();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return conn;
	}
	
	public Vector<TempMemberVO> getMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<TempMemberVO> vecList = new Vector<>();

		try {
			conn = this.getConnection(); 
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
