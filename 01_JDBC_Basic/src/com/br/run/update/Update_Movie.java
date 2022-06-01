package com.br.run.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update_Movie {

	public static void main(String[] args) {

		// KEY계정에 있는 TB_영화 테이블의 정보 수정하기
		// UPDATE(DML문) 실행 => 처리된 행수 (int 자료형) => 트랜잭션 처리
		
		// 1. UPDATE가 처리되면 받아낼 변수 선언
		int result = 0;
		
		// 2. JDBC 과정 중에 필요한 객체 생성
		Connection conn = null;
		Statement stmt = null;
		
		// 3. sql문 생성
		
		// 직접 값 입력하기
//		String sql = "UPDATE TB_영화 SET MOVIE_TITLE = '라디오스타', MOVIE_AGE = '12세이상', OPEN_DATE = SYSDATE, AUDIENCE = DEFAULT, RATE = DEFAULT "
//						+ "WHERE MOVIE_NO = 'NO.001'";
		
		// 사용자에게 값 입력받기
		Scanner sc = new Scanner(System.in);
		
		System.out.print("수정하고자하는 영화번호 : ");
		String no = sc.nextLine();		
		System.out.print("수정하고자하는 영화명 : ");
		String title = sc.nextLine();
		System.out.print("수정하고자하는 영화등급 : ");
		String grade = sc.nextLine();

		
		// 영화수정은 오늘날짜에 맞춰서 수정 => 새로 개봉했으니 평점, 관객수 모두 DEFAULT값으로 수정
		String sql = "UPDATE TB_영화 SET MOVIE_TITLE = '" + title + "', MOVIE_AGE = '" + grade + "', OPEN_DATE = SYSDATE, RATE = DEFAULT, AUDIENCE = DEFAULT "
						+ "WHERE MOVIE_NO = '" + no + "' "; 
		
		System.out.println(sql);
		

		try {
			
			// 4. JDBC driver 생성
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 5. Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			
			// 6. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 7. SQL문 전달하면서 결과 받기
			result = stmt.executeUpdate(sql);
			
			// 8. 트랜잭션 생성
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 9. 객체 반환
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 10. 결과값 반환
		if (result > 0) {
			System.out.println("정상적으로 수정되었습니다.");
		} else {
			System.out.println("수정하는데 실패했습니다.");
		}

	}

}
