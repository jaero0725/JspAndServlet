package jdbc;

import java.sql.*;
import java.util.Vector;

//Connection pool을 사용하는 클래스. 
public class TempMemberDAOCP {
	private ConnectionPool pool =null;

	public TempMemberDAOCP() {
		try {
			pool = ConnectionPool.getInstance();
		} catch (Exception e) {
			System.out.println("커넥션 얻어오기 실패");
		}
	}

	public Vector<TempMemberVO> getMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<TempMemberVO> vecList = new Vector<>();

		try {
			conn = pool.getConnection();
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
	    	if(conn != null) try{ pool.releaseConnection(conn);} catch(SQLException e){}
	    }
		return vecList;
	}
	
}
