package com.study.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	// 1. jdbc driver 등록 및 Connection 객체 생성
	public static Connection getConnection() {
		
		Connection conn=null;
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			
			conn=DriverManager.getConnection(prop.getProperty("url")
											,prop.getProperty("userid")
											,prop.getProperty("userpwd"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	// 2. Connection 전달받아서 commit 처리하는 메소드
	public static void commit(Connection conn) {
		
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 3. Connection 전달받아서 rollback 처리하는 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4. ResultSet 반납하는 메소드
	public static void close(ResultSet rset) {
		
		try {
			if(rset!=null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 5. Connection 반납하는 메소드
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 6. Statement 반납하는 메소드
	public static void close(Statement stmt) { // PreparedStatement의 부모 클래스이므로 Statement를 반납만해도 적용가능
		// 즉, Statement(부모)랑 PreparedStatement(자식) 모두 적용 => 다형성 적용됨!!!

		try {
			if (stmt != null && !stmt.isClosed()) { // Statment가 null 이 아니고 반납되지 않은 경우
				stmt.close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
