package com.br.run.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Insert_Reserve {
	


	public static void main(String[] args) {
		
		// * 내 pc(localhost) DB상 KEY계정에 있는 TB_영화테이블에 영화 정보 INSERT 하기
		// INSERT문 => 처리된 행수 (int 자료형)	=> 트랜잭션 처리(성공적으로 수행했으면 commit, 실패했으면 rollback)
		// INSERT 예시 ==> INSERT INTO TB_영화 VALUES (SEQ_영화.NEXTVAL, '프레쉬오브더보트', '12세이상', '1989/10/12', DEFAULT, DEFAULT);
		
		Scanner sc = new Scanner(System.in);

		// 1. 처리된 행수 받아줄 변수 설정
		int result = 0;

		// 2. jdbc과정에 필요한 객체 생성
		Connection conn = null;
		Statement stmt = null;

		// 3. 실행할 sql문 작성

		// 직접 값 입력받기
//		String reverse = "INSERT INTO TB_예매 VALUES (1, '주토피아', 1, '취소')";

		// 사용자에게 값 입력받기

		System.out.print("영화번호 : ");
		String no = sc.nextLine();
		System.out.print("영화명 : ");
		String title = sc.nextLine();
		System.out.print("인원수 : ");
		int people = sc.nextInt();
		sc.nextLine();
		System.out.print("예매확정/취소 : ");
		String confirm = sc.nextLine();

		String reverse = "INSERT INTO TB_예매 " + "VALUES ('" + no + "', '" + title + "', " + people + "," + "'" + confirm
				+ "' )";

		System.out.println(reverse);
		try {

			// 4. jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 5. Connection 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");

			// 6. Statement 객체 등록
			stmt = conn.createStatement();

			// 7. SQL문 전달하면서 실행

			result = stmt.executeUpdate(reverse);

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
