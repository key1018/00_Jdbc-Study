package com.br.run.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete_Movie {
	
	public static void main(String[] args) {
		

		// KEY계정에 있는 TB_영화 테이블의 정보 삭제하기
		// DELETE(DML문) 실행 => 처리된 행수 (int 자료형) => 트랜잭션 처리
		
		// 1. 결과값을 받아줄 변수 선언
		int result = 0;
		
		// 2. jdbc를 실행할 객체 선언
		Connection conn = null;
		Statement stmt = null;
		
		// 3. sql문 생성하기
		// TB_영화, TB_리뷰는 자동적으로 삭제됨 -> 외래키로 연결되어있기 떄문에!!
		// 하지만! TB_리뷰른 외래키로 연결되어있어도 
		String sql = "DELETE FROM TB_영화 WHERE MOVIE_NO = 'NO.006'";
		
		try {
			// 4. jdbc driver 선언
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 5. Connection 선언
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			
			// 6. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 7. sql문 전달하면서 값 받아내기
			result = stmt.executeUpdate(sql);
			
			// 8. 트랜잭션 실행
			if(result > 0) {
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
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 10. 결과값 반환
		if(result >0) {
			System.out.println("성공적으로 삭제했습니다.");
		} else {
			System.out.println("삭제하는데 실패했습니다.");
		}

	}

}
