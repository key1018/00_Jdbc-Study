package com.br.run.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.br.model.vo.Movie;

public class Select_Many {

	public static void main(String[] args) {

		
		// * 내 pc(localhost) DB상 KEY정에 있는 TB_영화 테이블에 모든 데이터 조회해보기
		// SELECT문 => 실행 결과 ResultSet에 먼저 받기 => ResultSet으로부터 데이터 하나씩 뽑아서 자바객체에 세팅
		
		// 1. 결과값을 담을 ArrayList 생성
		ArrayList<Movie> list = new ArrayList<>();
		
		// 2. jdbc에 필요한 객체 생성
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 3. sql문 생성
		String sql = "SELECT MOVIE_NO, MOVIE_TITLE, MOVIE_AGE, OPEN_DATE, RATE, AUDIENCE FROM TB_영화";
		
		try {
			
			// 4.jdbc driver 생성
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 5. Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			
			// 6. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 7. sql문 값 받아내기
			// 내가 실행할 sql문이 SELECT문일 경우 => Statement변수.executeQuery(쿼리문);  => ResultSet 반환
			// 내가 실행할 sql문이 DML일 경우 => Statement변수.executeUpdate(dml문);		=> int (처리된 행 수) 반환
			rset = stmt.executeQuery(sql);
			
			//8. ResultSet에 담겨있는 데이터들을 하나씩 뽑아서 
			while(rset.next()) {
				
				// 현재 rset의 커서라 가리키고 있는 한 행의 데이터들을 싹 뽑아서 Test객체에 하나하나씩 담기
				// rset으로 부터 "어떤 컬럼"에 담겨있는 값을 "어떤 타입"으로 뽑을 건지 제시
				// rset.getInt("컬럼명"), rset.getString("컬럼명"), rset.getDate("컬럼명")
				// -> 자바에서의 int타입,  자바에서의 String타입,    자바에서의 Date타입
				
				Movie movie = new Movie(); // 객체를 기본생성자로 먼저 생성
				
				movie.setMovieNo(rset.getString("MOVIE_NO")); // DB에 있는 MOVIE_NO자료 String 자료형으로 담기
				movie.setMovieTitle(rset.getString("MOVIE_TITLE"));
				movie.setMovieAge(rset.getString("MOVIE_AGE"));
				movie.setOpenDay(rset.getDate("OPEN_DATE")); // DB에 있는 OPEN_DATE를 DATE 자료형으로 담기
				movie.setRate(rset.getInt("RATE")); // DB에 있는 RATE를 int자료형으로 담기
				movie.setAudience(rset.getInt("AUDIENCE"));

				list.add(movie);

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

		// 10. 결과값 출력
		if (list.isEmpty()) {
			System.out.println("조회 결과가 없습니다.");
		} else {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}

	}

}