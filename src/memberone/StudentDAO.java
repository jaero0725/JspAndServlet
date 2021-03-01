package memberone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StudentDAO {

	//DAO를 싱글톤으로 만들어야함. -> useBean을 사용할 수 없음. 
	/*
	 	useBean으로 만든 것등르 전부 고쳐야 한다.
		StudentDAO dao = StudentDAO.getInstance();
	 */
	
	private static StudentDAO instance = null;
	private StudentDAO() {}
	public static StudentDAO getInstance() {
		if(instance == null) {
			synchronized (StudentDAO.class) {
				instance = new StudentDAO();
			}
		}
		return instance;
	}
	
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
	
	//아이디가 있는지 확인하는 메서드
	public boolean idCheck(String id) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement("select * from student where id =?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) result = false;
		} catch (Exception e) {
			System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return result;
	}
	
	//동을 매개변수로 받아 데이터베이스에서 우편번호를 검색해 온다. Vector로 받아옴.
	public Vector<ZipCodeVO> zipcodeRead(String dong){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<ZipCodeVO> vecList =new Vector<ZipCodeVO>();
		
		try {
			conn = this.getConnection(); 
			String strQuery = "select * from zipcode where dong like '" + dong +"%'";
			pstmt = conn.prepareStatement(strQuery);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipCodeVO vo = new ZipCodeVO();
				vo.setZipcode(rs.getString("zipcode"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setDong(rs.getString("dong"));
				vo.setRi(rs.getString("ri"));
				vo.setBunji(rs.getString("bunji"));
				vecList.addElement(vo);
			}
		} catch (Exception e) {
			System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return vecList;
	}
	
	//회원가입 insert
	public boolean memberInsert(StudentVO vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = this.getConnection(); 
			String strQuery = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
			System.out.println(vo.getId() + ","+ vo.getPass()+ ","+ vo.getName()+","+ vo.getPhone1()+","+ vo.getPhone2()+","+vo.getPhone3()+
					","+vo.getEmail()+ ","+vo.getZipcode() + "," +vo.getAddress1()+"," +vo.getAddress2());
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getZipcode());
			pstmt.setString(9, vo.getAddress1());
			pstmt.setString(10, vo.getAddress2());
			int count = pstmt.executeUpdate();
			if(count >0) flag = true;
		} catch (Exception e) {
			System.out.println(e);
	    } finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return flag;
	}
	
	//아이디를  받아서, 비밀번호를 출력해주는 함수.
	private String getPass(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pass = "";
		try {
			conn = this.getConnection(); 
			String strQuery = "select pass from student where id =?";
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, id); //받아온 id로 setting
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pass = rs.getString("pass");  //getPass하여 dbPass를 받아온다. 
			}
		} catch (Exception e) {
			System.out.println(e);
	    } finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		System.out.println("비밀번호는 " + pass +"이랍니다.");
		return pass;
	}
	
	// 로그인 하는 메서드 - id와 password를 비교하여 그 결과를 정수형으로 리턴해준다.
	public int loginCheck(String id, String pass) {
		int check = -1;// 초기값 -1 -> id가 없으면 -1출력한단 뜻.
		try {
			String dbPass = getPass(id).trim(); // getPass하여 dbPass를 받아온다.
			if(dbPass != "") {
				// 로그인 완료 - 비밀번호가 맞음.
				if (pass.equals(dbPass)) {
					check = 1;
				} 
				// 비밀번호가 틀림. 
				else {
					check = 0;
				}
			} 
		} catch (Exception e) {
			System.out.println(e);
		}
		return check;
	}
	
	// 회원을 삭제하는 Delete 메서드
	public int deleteMember(String id , String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPass = "";
		int result = -1 ; //결과값. 1 이면 성공, 0 이면 비밀번호 오류, -1 이면 애초에 없는 아ㅣ디
		try {
			conn = this.getConnection(); 
			//getPass 함수를 호출하여,db에 아이디에 맞는 비밀번호를 가져온다.
			dbPass = getPass(id);
			
			//아이디에 맞는 비밀번호 확인.
			if(dbPass != null) {
				//사용자가 입력한 비밀번호와 db에 있는 비밀번호와 맞는지 확인.
				//맞는경우
				if(dbPass.equals(pass)) {
					String sql = "delete from student where id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,id);
					pstmt.executeUpdate();
					result = 1; 
				} 
				// 비밀번호가 틀린경우
				else {
					result = 0;
				}
			}
			//없는 아이디 일 경우.
			else {
				result = -1;
			}
		} catch (Exception e) {
			System.out.println(e);
	    } finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return result;
	}
	
	//id를 통해 studentVO를 출력
	public StudentVO getMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO vo = null;
		try {
			conn = this.getConnection(); 
			String strQuery = "select * from student where id =?";
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new StudentVO();
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setEmail(rs.getString("email"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress1(rs.getString("Address1"));
				vo.setAddress2(rs.getString("Address2"));
			}
		} catch (Exception e) {
			System.out.println(e);
	    } finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return vo ;
	}
	
	//정보수정 처리하기 update
	public void updateMember(StudentVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = this.getConnection(); 
			String strQuery = "update student set pass=?, phone1=?, phone2=?, phone3=?, email=?, zipcode=?, address1=?, address2=? where id=?";
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getPhone1());
			pstmt.setString(3, vo.getPhone2());
			pstmt.setString(4, vo.getPhone3());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddress1());
			pstmt.setString(8, vo.getAddress2());
			pstmt.setString(9, vo.getId());
			pstmt.executeUpdate();
			System.out.println(vo.getId() + "님의 회원 수정 성공");
		} catch (Exception e) {
			System.out.println(e);
	    } finally{
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
	}
}


