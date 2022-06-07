package com.study.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// 공통 템블렛 (매번 반복적으로 작성될 코드를 메소드로 미리 정의해두기)
		
		// 모든 메소드 싹 다 static으로 정의해서 바로 메모리로 들어갈 수 있도록 설정
		// static으로 정의된 메소드는 클래스명.메소드명 으로 바로 호출 가능
		
		// 1. jdbc driver 등록 및 Connection객체 생성하여 반환해주는 메소드
		public static Connection getConnection() { // Connection 객체 반환

			Connection conn = null;

			try {
				// jdbc driver 등록
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// Connection 객체 등록
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// Connection 객체 반환
			return conn;

		}
		
		// 2. commit 처리해주는 메소드 (Connection 객체를 전달받아서 commit 실행하기)
		public static void commit(Connection conn) { // 반환해주는 것이 없으므로 void로 설정

			try {
				if (conn != null && !conn.isClosed()) { // Connection이 null이 아니고 반납되지 않은 경우 => commit
					conn.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		// 3. rollback 처리해주는 메소드 (Connection 객체를 전달받아서 rollback 실행하기)
		public static void rollback(Connection conn) {
			
			try {
				if(conn != null && !conn.isClosed()) { // Connection이 null이 아니고 반납되지 않은 경우 => rollback
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// JDBC용 객체들 전달받아서 반납처리해주는 메소드
		// 4. Statement객체 전달받아서 반납해주는 메소드
		public static void close(Statement stmt) { // 값을 반환하는것이 아닌 반납처리를 해주는 메소드이므로 void로 설정

			try {
				if (stmt != null && !stmt.isClosed()) { // Statement가 null이 아니고 반납되지 않은 경우 => 반납처리하기
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		// 5. Connection 객체 전달받아서 반납해주는 메소드
		public static void close(Connection conn) {
			
			try {
				if(conn != null && !conn.isClosed()) { // Connection이 null이 아니고 반납되지 않은 경우 => 반납처리
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 6. ResultSet 객체 전달받아서 반납해주는 메소드
		public static void close(ResultSet rset) {

			try {
				if (rset != null && !rset.isClosed()) {// ResultSet이 null이 아니고 반납되지 않은 경우 => 반납처리
					rset.close();

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
