package com.br.run.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Insert_Review {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// * 내 pc(localhost) DB상 KEY계정에 있는 TB_영화테이블에 영화 정보 INSERT 하기
		// INSERT문 => 처리된 행수 (int 자료형)	=> 트랜잭션 처리(성공적으로 수행했으면 commit, 실패했으면 rollback)

		// 1. 처리된 행수 받아줄 변수 설정
		int result = 0;

		// 2. jdbc과정에 필요한 객체 생성
		Connection conn = null;
		Statement stmt = null;

		// 3. 실행할 sql문 작성

		System.out.print("영화번호 : ");
		String no = sc.nextLine();
		System.out.print("영화명 : ");
		String title = sc.nextLine();
		System.out.print("리뷰내용 : ");
		String content = sc.nextLine();
		System.out.print("평점 : ");
		int rate = sc.nextInt();

		String review = "INSERT INTO TB_리뷰 " + "VALUES ('" + no + "', '" + title + "', '" + content + "', " + rate
				+ ")";

		// TB_리뷰
		System.out.println(review);

		try {

			// 4. jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 5. Connection 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");

			// 6. Statement 객체 등록
			stmt = conn.createStatement();

			// 7. SQL문 전달하면서 실행

			result = stmt.executeUpdate(review);

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
