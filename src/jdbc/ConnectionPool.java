package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	// 사용하지 않은 커넥션 , 초기 커넥션을 저장하는 변수

	private ArrayList<Connection> free;
	private ArrayList<Connection> used; // 사용중인 커넥션을 저장하는 변수
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "mytest";
	private String password = "mytest";
	private int initialCons = 10; // 최초로 초기 커넥션 수
	private int maxCons = 20; // 최대 커넥션수
	private int numCons = 0; // 총 connection 수

	// 싱글톤 패턴 사용
	private static ConnectionPool cp;

	public static ConnectionPool getInstance() {
		try {
			if (cp == null) {
				synchronized (ConnectionPool.class) {
					cp = new ConnectionPool();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cp;
	}

	private ConnectionPool() throws SQLException {
		// 초기 커넥션 개수를 각각의 arraylist에 저장할 수 있도록 초기 커넥션 수만큼 arraylist생성
		free = new ArrayList<Connection>(initialCons);
		used = new ArrayList<Connection>(initialCons);
		
			while (numCons < initialCons) {
				addConnection();
			}
	}
	
	private void addConnection()  throws SQLException{
		free.add(getNewConnection());
		
	}
	private Connection getNewConnection() throws SQLException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("About to connect to " + con);
		++numCons;
		return con;
	}

	// free에 있는 커넥션을 used로 옮기는 작업
	public synchronized Connection getConnection() throws SQLException {
		// free에 커넥션이 없으면, maxCons만큼 connection 을 더생성한다.
		if (free.isEmpty()) {
			while (numCons < maxCons) {
				addConnection();
			}
		}
		Connection _con = free.get(free.size() - 1);
		free.remove(_con);
		used.add(_con);
		return _con;
	}



	// used에 있는 커넥션을 free로 반납함.
	public synchronized void releaseConnection(Connection _con) throws SQLException {
		boolean flag = false;
		if (used.contains(_con)) {
			used.remove(_con);
			numCons--;
			flag = true;
		} else {
			throw new SQLException("커넥션 풀이 없어요...");
		}
		try {
			if (flag) {
				free.add(_con);
				numCons++;
			} else {
				_con.close();
			}
		} catch (SQLException e) {
			try {
				_con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public int getMaxCons() {
		return maxCons;
	}

	public int getNumCons() {
		return numCons;
	}
}
