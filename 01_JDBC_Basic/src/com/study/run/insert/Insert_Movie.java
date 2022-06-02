package com.study.run.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;




public class Insert_Movie {
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
	
		/*
		 * *** JDBC용 객체 (순서 중요!!)
		 * - DriverManager : DB와 연결된 Connection 객체를 생성하기 위한 객체
		 * - Connection : DB에 접속된 객체 (DB의 연결정보를 담고있는 객체) => DB와 연결되어있음
		 * - [Prepared] Statement : 연결된 DB에 sql문을 전달해서 실행하고 그 결과문을 받아내는 객체 ==>> 핵심!!!!!!
		 * - ResultSet : SELECT문 실행 후 조회된 결과들이 담겨있는 객체 (SELECT문 사용할때만 사용)
		 * 
		 * ** JDBC 과정 (순서 중요)
		 * 1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 클래스로 등록 (ojdbc6 파일에 들어있음)
		 * 2) Connection 등록 : 연결하고자 하는 DB정보를 작성해서 해당 DB와 연결하면서 생성
		 * 3) Statement 객체 생성 : Connection 객체를 이용해서 생성 (sql문 실행 및 결과받는 객체)
		 * 4) sql문 전달하면서 실행 : Statemenct 객체를 이용해서 sql문 실행
		 * 5) 결과받기
		 * 		> SELECT문 실행 => ResultSet객체 (조회된 데이터들이 담겨있음) => 6_1)번 과정 실행
		 * 		> 	 DML문 실행 => int (처리된 행 수) => 6_2)번 과정 실행(ex: 2개 행이 실행되면 2가 반환됨)
		 * 		*DML문 : INSERT, DELETE, UPDATE
		 * 
		 * 6_1) ResultSet에 담겨있는 데이터들을 하나씩 뽑아서 자바 객체에 옮겨 담기
		 * 6_2) (개발자가 직접) 트랜잭션 처리(성공이면 commit, 실패면 rollback)
		 * 
		 * 7) 다 사용했으면 JDBC용 객체 자원 반환하기 (close)	=> 생성된 역순으로 반납
		 * 
		 */
		
		// * 내 pc(localhost) DB상 KEY계정에 있는 TB_영화테이블에 영화 정보 INSERT 하기
		// INSERT문 => 처리된 행수 (int 자료형)	=> 트랜잭션 처리(성공적으로 수행했으면 commit, 실패했으면 rollback)
		// INSERT 예시 ==> INSERT INTO TB_영화 VALUES (SEQ_영화.NEXTVAL, '프레쉬오브더보트', '12세이상', '1989/10/12', DEFAULT, DEFAULT);
		
		// 1. 처리된 행수 받아줄 변수 설정
		int result = 0;

		// 2. jdbc과정에 필요한 객체 생성
		Connection conn = null;
		Statement stmt = null;

		// 3. 실행할 sql문 작성

		// 직접 입력하기
//		String sql = "INSERT INTO TB_영화 VALUES (SEQ_영화.NEXTVAL, '주토피아', '5세이상', '2020/08/23', DEFAULT, DEFAULT)";

		// 사용자에게 입력받기

		System.out.print("영화명 : ");
		String title = sc.nextLine();
		System.out.print("영화등급 : ");
		String grade = sc.nextLine();
		System.out.print("개봉일 : ");
		String date = sc.nextLine();

		String sql = "INSERT INTO TB_영화 " + "VALUES ('NO.' || LPAD(SEQ_영화.NEXTVAL, 3, '0'), '" + title + "', '" + grade
				+ "', '" + date + "', DEFAULT, DEFAULT)";

		// TB_영화
		System.out.println(sql);

		try {

			// 4. jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 5. Connection 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");

			// 6. Statement 객체 등록
			stmt = conn.createStatement();

			// 7. SQL문 전달하면서 실행

			result = stmt.executeUpdate(sql);

			// 8. 트랜잭션 처리
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				// 9. 객체 반환하기(역순으로)
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 10. 결과값 확인하기
		if (result > 0) {
			System.out.println("성공적으로 입력되었습니다.");
		} else {
			System.out.println("입력하는데 실패했습니다.");
		}

	}

}
