package com.br.run.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.br.model.vo.Movie;

public class Select_One {

	public static void main(String[] args) {
		
		
		// * 내 pc(localhost) DB상 KEY정에 있는 TB_영화 테이블에 모든 데이터 조회해보기
		// SELECT문 => 실행 결과 ResultSet에 먼저 받기 => ResultSet으로부터 데이터 하나씩 뽑아서 자바객체에 세팅
		
		// 1. 결과값을 담을 Movie 객체 생성
		Movie list = null; // 조회된 데이터가 있을 때 객체 생성
		// 조회결과의 유무를 확인하기 위해서 객체를 미리 생성하지않고 null로 선언하기
		
		// 2. jdbc에 사용할 객체 생성
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 3. sql문 생성
		
		// 직접 값 입력받기
//		String sql = "SELECT MOVIE_NO, MOVIE_TITLE, MOVIE_AGE, OPEN_DATE, RATE, AUDIENCE FROM TB_영화 WHERE MOVIE_NO = 'NO.001'";
		
		// 사용자게에 값 입력받기
		Scanner sc = new Scanner(System.in);
		
		System.out.print("조회하고자하는 영화번호 : ");
		String no = sc.nextLine();
		
		String sql = "SELECT MOVIE_NO, MOVIE_TITLE, MOVIE_AGE, OPEN_DATE, RATE, AUDIENCE FROM TB_영화 "
						+ "WHERE MOVIE_NO = '" + no + "' ";
		
		try {
			// 4. jdbc driver 생성
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 5. Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			
			// 6. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 7. sql값을 rset에 담기
			rset = stmt.executeQuery(sql);
			
			// 8. rset에 담은 값을 Movie객체에 담기
			
			// 매개변수생성자로 바로 담기

			if (rset.next()) {
				list = new Movie(rset.getString("MOVIE_NO"), rset.getString("MOVIE_TITLE"), rset.getString("MOVIE_AGE"),
						rset.getDate("OPEN_DATE"), rset.getInt("RATE"), rset.getInt("AUDIENCE"));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 9. 객체 반환
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 10. 결과값 반환
		if (list == null) {
			System.out.println("조회결과가 없습니다.");
		} else {
			System.out.println(list);
		}
	}

}
