package com.study.run.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete_Review {

	public static void main(String[] args) {

		// KEY계정에 있는 TB_리뷰 테이블의 정보 삭제하기
		// DELETE(DML문) 실행 => 처리된 행수 (int 자료형) => 트랜잭션 처리

		// 1. 결과값 담을 변수 선언
		int result = 0;

		// 2. jdbc driver 객체 미리 선언
		Connection conn = null;
		Statement stmt = null;

		// 3. sql문 생성

		// 직접 값 입력하기
//		String sql = "DELETE FROM TB_리뷰 WHERE MOVIE_NO = 'NO.001'";

		// 사용자에게 값 입력받기
		Scanner sc = new Scanner(System.in);

		System.out.print("삭제하고자하는 영화명 : ");
		String title = sc.nextLine();

		String sql = "DELETE FROM TB_리뷰 WHERE MOVIE_TITLE = '" + title + "' ";

		System.out.println(sql);

		try {

			// 4. jdbc driver 생성
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 5. Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");

			// 6. Statement 객체 생성
			stmt = conn.createStatement();

			// 7. sql값 받아내기
			result = stmt.executeUpdate(sql);

			// 8. 트랜잭션 실행
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
				// 9. 객체 반환
				conn.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 10. 결과값 반환
		if (result > 0) {
			System.out.println("성공적으로 삭제했습니다.");
		} else {
			System.out.println("삭제하는데 실패했습니다.");
		}

	}

}
