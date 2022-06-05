package com.study.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.study.member.model.vo.Member;
import com.study.member.model.vo.Movie;
import com.study.member.model.vo.Review;

public class MovieDao {
	
	// DAO (Data Access Object)
	// DB에 직접 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과받기(즉, jdbc과정을 여기서)
	// 결과를 MemberController로 다시 반환
	
	/**
	 * 1. 회원 가입 요청처리하는 jdbc
	 * @param userId : 사용자가 입력한 아이디
	 * @param userPwd : 사용자가 입력한 비밀번호
	 * @param userName : 사용자가 입력한 이름
	 * @param gender : 사용자가 입력한 성별
	 * @param age : 사용자가 입력한 나이
	 * @param phone : 사용자가 입력한 전화번호
	 * @return : 회원가입 성공여부에 따른 int값 반환 (가입 성공 : 1 | 실패 : 0)
	 */
	public int insertMember(String userId, String userPwd, String userName, String gender, int age, String phone) {
		
		// insert문 => int자료형에 결과값 담기 => Member 객체에 담기 => 트랜잭션 실행
		int result = 0;
		
		// jdbc를 실행할 객체 생성
		Connection conn = null;
		Statement stmt = null;
		
		// 실행할 sql문 생성 (완성된 sql문)
		String sql = "INSERT INTO TB_회원 VALUES ('" + userId + "', '" + userPwd + "', '" + userName + "', '" + gender + "', " 
												+ age + ", '" + phone + "', SYSDATE" + ")";		
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// Statement 객체 등록
			stmt = conn.createStatement();
			// int 자료형에 결과값 담기
			result = stmt.executeUpdate(sql);
			
			// 트랜잭션 실행
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
				// 다 쓴 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result;

	}
	
	/**
	 * 2. 영화 정보 추가 요청처리하는 jdbc
	 * @param movieTitle : 추가자고자하는 영화제목
	 * @param movieAge : 추가하고자하는 영화등급
	 * @param openDate : 추가하고자하는 영화의 개봉일
	 * @return : 영화정보 추가의 성공여부에 따른 int값 반환 (추가 성공 : 1 | 실패 : 0) 
	 */
	public int insertMovie(String movieTitle, String movieAge, String openDate) {
		
		// insert문 => int자료형에 결과값 담기 => Member 객체에 담기 => 트랜잭션 실행
		int result = 0;
		
		// jdbc 객체 등록
		Connection conn = null;
		Statement stmt = null;
		
		// 실행할 sql문 생성
		String sql = "INSERT INTO TB_영화 VALUES ('NO.' || LPAD(SEQ_영화.NEXTVAL,3,'0'), '" + movieTitle + "', '" + movieAge + "', '" + openDate + "', "
												+ "DEFAULT, DEFAULT)";
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// sql 실행 및 결과받기
			result = stmt.executeUpdate(sql);
			
			// 트랜잭션 실행
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
				// 다 쓴 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result;

	}
	
	public int insertReserve(String movieNo, int audience, String confirmed) {
		
		// insert문 => int자료형에 결과값 담기 => Member 객체에 담기 => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		// 실행할 sql문 새성
		String sql = "INSERT INTO TB_예매 VALUES ('" + movieNo + "', " + audience + ", '" + confirmed + "')";
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// Statement 객체 등록
			stmt = conn.createStatement();
			
			// sql문 실행 및 결과받기
			result = stmt.executeUpdate(sql);
			
			// 트랜잭션 실행
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
				// 다 쓴 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 결과값 return
		return result;
		
	}
	
	/**
	 * 4. 영화 리뷰 추가 요청처리하는 jdbc
	 * @return 리뷰 추가 성공여부 확인하는 int값 (리뷰 등록 성공 : 1 | 실패 : 0)
	 */
	public int insertReview(String userId, String movieNo, String movieTitle, String content, int rate) {
		
		// insert문 => int자료형에 결과값 담기 => Member 객체에 담기 => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		// 실행할 sql문 새성
		String sql = "INSERT INTO TB_리뷰 VALUES ('" + userId + "', '" + movieNo + "', '" + movieTitle + "', '" + content
												+ "', " + rate + ")";
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// Statement 객체 등록
			stmt = conn.createStatement();
			
			// sql문 실행 및 결과받기
			result = stmt.executeUpdate(sql);
			
			// 트랜잭션 실행
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
				// 다 쓴 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result;

	}
	
	/**
	 * 5_1. 전체 영화 조회 요청처리하는 jdbc
	 * @return 전체 영화 조회 결과가 담긴 ArrayList<Movie> (텅 빈 리스트 | 조회결과가 담긴 리스트)
	 */
	public ArrayList<Movie> selectMovie(){
		
		// select => ResultSet => ArrayList<Movie>
		ArrayList<Movie> movieList = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문 생성
		String sql = "SELECT * FROM TB_영화";
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// Statement 객체 등록
			stmt = conn.createStatement();
			
			// sql문 실행 및 결과받기
			rset = stmt.executeQuery(sql);
			
			// movieList에 조회되는 값 넣기
			while (rset.next()) {

				movieList.add(new Movie(rset.getString("MOVIE_NO"), rset.getString("MOVIE_TITLE"),
										rset.getString("MOVIE_AGE"), rset.getDate("OPEN_DATE"), rset.getInt("RATE"),
										rset.getInt("AUDIENCE")));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 다 쓴 객체 반납
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return movieList;

	}
	
	/**
	 * 5_2. 전체 영화 리뷰 조회 요청처리하는 메소드
	 * @return 전체 리뷰 조회 결과가 담긴 ArrayList<Review> (텅 빈 리스트 | 조회결과가 담긴 리스트)
	 */
	public ArrayList<Review> selectReview(){
		
		// select => ResultSet => ArrayList<Movie>
		ArrayList<Review> reviewList = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문 생성
		String sql = "SELECT * FROM TB_리뷰";
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// Statement 객체 등록
			stmt = conn.createStatement();
			
			// sql문 실행 및 결과받기
			rset = stmt.executeQuery(sql);
			
			// movieList에 조회되는 값 넣기
			while (rset.next()) {

				reviewList.add(new Review(rset.getString("USER_ID"), rset.getString("MOVIE_NO"), 
										  rset.getString("MOVIE_TITLE"), rset.getString("CONTENT"), rset.getInt("RATE")));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 다 쓴 객체 반납
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return reviewList;

	}
	
	/**
	 * 6. 영화 이름(키워드)에 따른 리뷰를 조회하는 jdbc
	 * @param keyword : 조회하고자하는 영화이름(키워드)
	 * @return : 조회결과가 담겨있는 ArrayList<Review> 
	 */
	public ArrayList<Review> selectKeyReview(String keyword) {
		
		// select => ResultSet => ArrayList<Review> 객체에 담기
		ArrayList<Review> reviewList = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문
		String sql = "SELECT * FROM TB_리뷰 WHERE MOVIE_TITLE LIKE '%" + keyword + "%' ";

		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// Statement 객체 등록
			stmt = conn.createStatement();
			// sql문 실행 및 결과받기
			rset = stmt.executeQuery(sql);
			
			// Review 객체에 조회결과 담기
			while (rset.next()) {

				reviewList.add(new Review(rset.getString("USER_ID"), rset.getString("MOVIE_NO"), 
										  rset.getString("MOVIE_TITLE"), rset.getString("CONTENT"), rset.getInt("RATE")));

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 다 쓴 객체 반납
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 결과값 return
		return reviewList;

	}
	
	/**
	 * 7. 회원 정보 수정 요청을 처리하는 jdbc
	 * @param userId : 수정을 원하는 회원 아이디
	 * @param pwd ~ phone : 수정할 정보들
	 * @return : 회원 정보가 수정됐는지 확인하기 위한 int값 (수정 성공 : 1 | 실패 : 0)
	 */
	public int updateMember(String userId, String pwd, String name, int age, String phone) {
		
		// insert문 => int자료형에 결과값 담기 => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		// 실행할 sql문 새성
		String sql = "UPDATE TB_회원 SET USER_PWD = '" + pwd + "', USER_NAME = '" + name + "', AGE = " + age + ", PHONE = '" + phone + "' "
					+ "WHERE USER_ID = '" + userId + "' ";
		
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// Statement 객체 등록
			stmt = conn.createStatement();
			
			// sql문 실행 및 결과받기
			result = stmt.executeUpdate(sql);
			
			// 트랜잭션 실행
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
				// 다 쓴 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result;

	}
	
	
	/**
	 * 8. 회원 삭제(탈퇴) 요청을 처리하는 jdbc 
	 * @param userId : 삭제(탈퇴)를 원하는 회원아이디
	 * @return : 삭제처리를 확인하기위한 int값 (삭제 성공 : 1 | 실패 : 0)
	 */
	public int deleteMember(String userId) {
		
		// insert문 => int자료형에 결과값 담기 => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		// 실행할 sql문 새성
		String sql = "DELETE FROM TB_회원 WHERE USER_ID = '" + userId + "' ";

		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// Statement 객체 등록
			stmt = conn.createStatement();
			
			// sql문 실행 및 결과받기
			result = stmt.executeUpdate(sql);
			
			// 트랜잭션 실행
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
				// 다 쓴 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result;

	}
	
	/**
	 * 9. 로그인 요청을 처리하는 jdbc
	 * @param userId : 로그인을 원하는 회원아이디
	 * @param userPwd : 로그인을 원하는 회원비밀번호
	 * @return : 로그인 성공/실패에 따른 값을 담은 Member 객체
	 */
	public Member loginMember(String userId, String userPwd) {
		
		// select => ResultSet => Member 객체에 담기
		Member m = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM TB_회원 WHERE USER_ID = '" + userId + "' AND USER_PWD = '" + userPwd + "' ";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				m = new Member(rset.getString("USER_ID"), rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"), rset.getString("GENDER"),
							   rset.getInt("AGE"), rset.getString("PHONE"), rset.getDate("ENROLL_DATE"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return m;

	}

}
