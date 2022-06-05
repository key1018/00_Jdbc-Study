package com.study2.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.study2.member.model.vo.Member;
import com.study2.member.model.vo.Movie;
import com.study2.member.model.vo.Review;

public class MovieDao {
	
	// DAO (Data Access Object)
	// DB에 직접 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과받기(즉, jdbc과정을 여기서)
	// 결과를 MemberController로 다시 반환
	
	/*
	 *  * Statement와 PreparedStatement의 특징
	 *  - 둘 다 sql문을 실행하고 결과를 받아내는 객체 (둘 중 하나 이용하면됨)
	 *  - Statement가 PreparedStatement의 부모 (상속구조)
	 *  
	 *  * Statement와 PreparedStatement의 차이점
	 *  - Statement 같은 경우 sql문을 바로 전달하면서 실행하는 객체
	 *    (즉, sql문을 완성형태로 만들어야됨! == 사용자가 입력한 값이 다 채워진 상태로)
	 *    
	 *     > 기존의 Statement 방식
	 *     1) Connection 객체를 통해서 Statement객체 생성
	 *     		> stmt = conn.createStatement();
	 *     2) Statement 객체를 통해서 "완성된 sql문"을 실행 및 결과 받기
	 *     		> 결과 == stmt.execute(Query/Update)(완성된sql문);	=> 값을 바로 전달하기떄문에 sql문은 무조건 완성되어있어야함
	 *     
	 *  - PreparedStatment 같은 경우 "미완성된 sql문"을 잠시 보관해둘 수 있는 객체
	 *    (즉, 사용자가 입력한 값들을 채워두지 않고 각각 들어갈 공간을 미리 확보만 해두면됨)
	 *    
	 *   * PreparedStatement 방식
	 *    1) Connection 객체를 통해 PreparedStatement객체 생성
	 *    		> pstmt = conn.prepareStatement([완성/미완성된]sql문);
	 *  
	 *    2) pstmt에 담긴 sql문이 미완성된 상태일 경우 우선은 완성시켜야됨
	 *    		> pstmt.setXXX(1, 대체할값);
	 *    		> pttmt.setXXX(2, 대체할값); 
	 *    		> ...	
	 *   
	 *    3) sql문 실행 및 결과받기
	 *    		> 결과 = pstmt.execute(Query/Update)();
	 *    		  단, sql문을 전달하면서 호출하는 것이 아닌 '그냥' 호출
	 */
	
	
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
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성 (완성된 sql문)	
		
		String sql = "INSERT INTO TB_회원 VALUES (?, ?, ?, ?, ?, ?, SYSDATE)";
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 생성(미완성된 sql문)
			pstmt = conn.prepareStatement(sql); 
			
			
			// *** result = pstmt.executeUpdate();  => 미완성된 sql문이 실행되므로 무조건 sql문을 완성시킨 후에 실행해야함!!!!
			
			// pstmt.setString(물음표(홀더)자리, 대체할값)  => '대체할값' (양옆에 홑따움표로 감싸준 데이터가 들어감) => 문자로 들어가고싶은 데이터는 무조건 setString 으로 해야함
			// pstmt.setInt(물음표(홀더)자리, 대체할값) => 대체할값 (양 옆에 홑따움표로 안감싸줌)
			
			// sql문을 완성된 문장으로 바꾸기
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			pstmt.setString(3, userName);
			pstmt.setString(4, gender);
			pstmt.setInt(5, age);
			pstmt.setString(6, phone);
			
			// int 자료형에 결과값 담기
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성
		
		String sql = "INSERT INTO TB_영화 VALUES ('NO.' || LPAD(SEQ_영화.NEXTVAL,3,'0'), ?, ?, ?, DEFAULT, DEFAULT)";
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 생성(미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql문으로 바꾸기
			pstmt.setString(1, movieTitle);
			pstmt.setString(2, movieAge);
			pstmt.setString(3, openDate);
			
			// sql 실행 및 결과받기
			result = pstmt.executeUpdate(sql);
			
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result;

	}
	
	/**
	 * 3. 영화 예매 요청을 처리하는 jdbc
	 * @param movieNo : 예매하고자하는 영화번호
	 * @param audience : 예매하고자하는 영화제목
	 * @param confirmed : 예매상태확인 (확정/취소)
	 * @return : 영화 예매의 결과값을 보여주는 int값 (예매 성공 : 1 | 실패 : 0)
	 */
	public int insertReserve(String movieNo, int audience, String confirmed) {
		
		// insert문 => int자료형에 결과값 담기 => Member 객체에 담기 => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성
		String sql = "INSERT INTO TB_예매 VALUES (?, ?, ?)";
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 생성(미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			// 완성된 sql문으로 바꾸기
			pstmt.setString(1, movieNo);
			pstmt.setInt(2, audience);
			pstmt.setString(3, confirmed);
			
			// sql문 실행 및 결과받기
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성		
		String sql = "INSERT INTO TB_리뷰 VALUES (?, ?, ?, ?, ?)";
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 생성(미완성된 sql문)
			pstmt = conn.prepareStatement(sql);

			// 완성된 sql문으로 바꾸기
			pstmt.setString(1, userId);
			pstmt.setString(2, movieNo);
			pstmt.setString(3, movieTitle);
			pstmt.setString(4, content);
			pstmt.setInt(5, rate);
			
			// sql문 실행 및 결과받기
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문 생성
		String sql = "SELECT * FROM TB_영화";
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 등록 (완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			
			// sql문 실행 및 결과받기
			rset = pstmt.executeQuery();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문 생성
		String sql = "SELECT * FROM TB_리뷰";
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 등록 (완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			// sql문 실행 및 결과받기
			rset = pstmt.executeQuery();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문 생성
		
		// 방법1
//		String sql = "SELECT * FROM TB_리뷰 WHERE MOVIE_TITLE LIKE ?";
		
		// 방법2
		String sql = "SELECT * FROM TB_리뷰 WHERE MOVIE_TITLE LIKE % || ? || %";
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 생성(미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql문으로 바꾸기
			
			// 방법1
//			pstmt.setString(1, "%" + keyword + "%");
			
			// 방법2
			pstmt.setString(2, keyword);
			
			// sql문 실행 및 결과받기
			rset = pstmt.executeQuery();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성 		
		String sql = "UPDATE TB_회원 SET USER_PWD = ?, USER_NAME = ?, AGE = ?, PHONE = ? WHERE USER_ID = ?";
		
		
		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 생성(미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql문으로 바꾸기
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setString(4, phone);
			
			// sql문 실행 및 결과받기
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성
		String sql = "DELETE FROM TB_회원 WHERE USER_ID = ?";

		System.out.println(sql);
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 등록
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 생성(미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql문으로 바꾸기
			pstmt.setString(1, userId);
			
			// sql문 실행 및 결과받기
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM TB_회원 WHERE USER_ID = ? AND USER_PWD = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			// PreparedStatement 객체 생성(미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql문으로 바꾸기
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return m;

	}

}
