package boardone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import memberone.ZipCodeVO;

public class BoardDAO {
	private static BoardDAO  instance = null;
	private BoardDAO () {}
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	public void insertArticle(BoardVO article) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0 ;
		String sql = "";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(ref) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref = rs.getInt(1) + 1; 
			} else {
				ref = 1;
			}
			//답변 글인 경우 
			if(num!=0) {
				sql = "update board set step = step+1 where ref = ? and step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step + 1;
				depth = depth + 1;
			}
			//새글인 경우 
			else {
				ref = number;
				step = 0;
				depth = 0;
			} 
			//쿼리 작성. 
			sql = "insert into board(num, writer, email, subject, pass, regdate, ref, step, depth, content, ip) values "
					+ "(board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4,article.getPass());
			pstmt.setTimestamp(5, article.getRegdate());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, depth);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			pstmt.executeUpdate();
			
		}  catch (Exception e) {
			System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		//select max(ref) from board
	}
	
	//조회수 증가 메서드
	public void upCount(int num) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update board set readcount = readcount +1 where num =?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
	}
	
	public BoardVO getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		BoardVO vo = null;
		try {
			conn = ConnUtil.getConnection();
			upCount(num); //조회수 증가 
			pstmt = conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setWriter(rs.getString("writer"));
				vo.setEmail(rs.getString("email"));
				vo.setSubject(rs.getString("subject"));
				vo.setPass(rs.getString("pass"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setRef(rs.getInt("ref"));
				vo.setStep(rs.getInt("step"));
				vo.setDepth(rs.getInt("depth"));
				vo.setContent(rs.getString("content"));
				vo.setIp(rs.getString("ip"));
			}
		}  catch (Exception e) {
			System.out.println("getArticle 메서드 오류 ");
			System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return vo;
	}
	
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		int x= 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}  catch (Exception e) {
			System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return x;
	}
	
	public List<BoardVO> getArticles(int start, int end) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List<BoardVO> articleList = new ArrayList<>(end - start + 1);
	      try {
	         conn = ConnUtil.getConnection();
	         // 수정2
	         // pstmt = conn.prepareStatement("select * from board order by num desc"); 
	         pstmt = conn.prepareStatement("select * from (select rownum rnum, num, writer, email, "
	               + "subject, pass, regdate, readcount, ref, step, depth, content, ip from ("
	               + "select * from board order by ref desc, step asc)) where rnum >= ? and rnum <= ?"); 
	         pstmt.setInt(1, start);
	         pstmt.setInt(2, end);
	         // 수정3
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            BoardVO article = new BoardVO(rs.getInt("num"), rs.getString("writer"), 
	                  rs.getString("email"), rs.getString("subject"), rs.getString("pass"), 
	                  rs.getInt("readcount"), rs.getInt("ref"), rs.getInt("step"), 
	                  rs.getInt("depth"), rs.getTimestamp("regdate"), 
	                  rs.getString("content"), rs.getString("ip"));
	            articleList.add(article);
	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         if(rs != null) try {rs.close();} catch (Exception e2) {}
	         if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
	         if(conn != null) try {conn.close();} catch (Exception e2) {}
	      }
	      return articleList;
	   }
	
	//비밀번호를 가져오는 메서드 
	public String getPass(int num) {
		System.out.println("getPass | num : " + num );
		System.out.println("getPass | getPass 호출 2");
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String pass = "";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select pass from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pass = rs.getString("pass");
				System.out.println("getPass | 비번 : " + pass);
			}
		}  catch (Exception e) {
			System.out.println("getPass | getPass 메서드 오류 ");
			System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return pass;
	}
	
	//글 수정
	public int updateArticle(BoardVO article) {
		System.out.println("update Article 실행");
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String dbpasswd ="";
		String sql="";
		int result = -1;
		
		try {
			conn = ConnUtil.getConnection();
			dbpasswd = getPass(article.getNum());
			if(dbpasswd.equals(article.getPass())) {
				sql = "update board set writer=?, email=?, subject=?, content=? where num =?";
				pstmt = conn.prepareStatement(sql);  
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getEmail());
				pstmt.setString(3, article.getSubject());
				pstmt.setString(4, article.getContent());
				pstmt.setInt(5, article.getNum());
				pstmt.executeUpdate();
				result = 1; //수정 성공
			} else {
				result = -1;
			}
		}  catch (Exception e) {
			System.out.println("getArticle 메서드 오류 ");
			System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return result;
	}
	
	public int deleteArticle(int num, String pass) {
		System.out.println("delete Article 실행");
		Connection conn = null;
		PreparedStatement pstmt =null;
		String dbpasswd ="";
		int result = -1;
		try {
			dbpasswd = getPass(num); //db비밀번호 받아오기
			if(dbpasswd.equals(pass)) {
				conn = ConnUtil.getConnection();
				System.out.println("delete | 비밀번호가 같음.");
				pstmt = conn.prepareStatement("delete from board where num =?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				result = 1; //수정 성공
			} else {
				System.out.println("delete | 비밀번호가 틀림.");
				result = -1;
			}
		}  catch (Exception e) {
			System.out.println("delete | 오류가 떨어짐..");
			System.out.println(e);
	    }finally{
	    	if(pstmt != null) try{ pstmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ conn.close();} catch(SQLException e){}
	    }
		return result;
	}
	/*
	 * new ArrayList<BoardVO> (end- start + 1);
	 * 
	 */
}
