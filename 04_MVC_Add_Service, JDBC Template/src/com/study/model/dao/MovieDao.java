package com.study.model.dao;

import static com.study.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.study.model.vo.Member;
import com.study.model.vo.Movie;
import com.study.model.vo.Review;

public class MovieDao {
	
	
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
	public int insertMember(Connection conn, Member m) { // MovieService클래스의 insertMember메소드에서 Connection, Member 객체
															// 전달받음

		// insert => int => 트랜잭션실행
		int result = 0;

		// PreparedStatement 객체 생성 (Statement 객체는 메소드마다 다르게 쓰이므로 JDBCTemplate 클래스에서 미리
		// 메소드 생성 x)
		PreparedStatement pstmt = null;

		// 실행할 sql문 생성
		String sql = "INSERT INTO TB_회원 VALUES(?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			// pstmt 객체 완성하기 (미완성된 sql문)
			pstmt = conn.prepareStatement(sql);

			// sql문 완성하기
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getPhone());

			// sql문 실행 및 결과받기
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납하기 (Connecion은 아직 반납 x => 트랜잭션 실행을 아직 안했으므로)
			close(pstmt); // PreparedStatement 객체 전달하면서  JDBCTemplate 클래스의 close메소드 실행
		}

		// 결과값 return
		return result; // MovieService().insertMember에 결과값 반환하기 (추가 성공 : 1 | 실패 : 0)

	}
	
	/**
	 * 2. 영화 정보 추가 요청처리하는 jdbc
	 * @param movieTitle : 추가자고자하는 영화제목
	 * @param movieAge : 추가하고자하는 영화등급
	 * @param openDate : 추가하고자하는 영화의 개봉일
	 * @return : 영화정보 추가의 성공여부에 따른 int값 반환 (추가 성공 : 1 | 실패 : 0) 
	 */
	public int insertMovie(Connection conn, String movieTitle, String movieAge, String openDate) {
		
		// insert => int형 => 트랜잭션 실행
		int result = 0;
		
		// PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성(미완성된 sql문)
		String sql = "INSERT INTO TB_영화 VALUES ('NO.' || LPAD(SEQ_영화.NEXTVAL,3,'0'), ?, ?, ?, DEFAULT, DEFAULT)";
		
		try {
			// pstmt 객체 완성하기
			pstmt = conn.prepareStatement(sql);
			// sql문 완성하기
			pstmt.setString(1, movieTitle);
			pstmt.setString(2, movieAge);
			pstmt.setString(3, openDate);
			
			// sql문 실행 및 결과받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납(pstmt만 반납하기 => 아직 트랜잭션 실행 x)
			close(pstmt); // PreparedStatement 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
		}
		
		// 결과값 return
		return result; // MovieService().insertMovie에 결과값 반환하기 (추가 성공 : 1 | 실패 : 0)

	}
	
	/**
	 * 3. 영화 예매 요청을 처리하는 jdbc
	 * @param movieNo : 예매하고자하는 영화번호
	 * @param audience : 예매하고자하는 영화제목
	 * @param confirmed : 예매상태확인 (확정/취소)
	 * @return : 영화 예매의 결과값을 보여주는 int값 (예매 성공 : 1 | 실패 : 0)
	 */
	public int insertReserve(Connection conn, String movieNo, int audience, String confirmed) {
		
		// insert => int형 => 트랜잭션 실행
		int result = 0;
		
		// PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성(미완성된 sql문)
		String sql = "INSERT INTO TB_예매 VALUES (?,?,?)";
		
		try {
			// pstmt 객체 완성하기
			pstmt = conn.prepareStatement(sql);
			// sql문 완성하기
			pstmt.setString(1, movieNo);
			pstmt.setInt(2, audience);
			pstmt.setString(3, confirmed);
			
			// sql문 실행 및 결과받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납(pstmt만 반납하기 => 아직 트랜잭션 실행 x)
			close(pstmt); // PreparedStatement 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
		}
		
		// 결과값 return
		return result; // MovieService().insertMovie에 결과값 반환하기 (추가 성공 : 1 | 실패 : 0)
		
		
	}
	
	/**
	 * 4. 영화 리뷰 추가 요청처리하는 jdbc
	 * @return 리뷰 추가 성공여부 확인하는 int값 (리뷰 등록 성공 : 1 | 실패 : 0)
	 */
	public int insertReview(Connection conn, String userId, String movieNo, String movieTitle, String content, int rate) {
		
		// insert => int형 => 트랜잭션 실행
		int result = 0;
		
		// PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성(미완성된 sql문)
		String sql = "INSERT INTO TB_리뷰 VALUES (?,?,?,?,?)";
		
		try {
			// pstmt 객체 완성하기
			pstmt = conn.prepareStatement(sql);
			// sql문 완성하기
			pstmt.setString(1, userId);
			pstmt.setString(2, movieNo);
			pstmt.setString(3, movieTitle);
			pstmt.setString(4, content);
			pstmt.setInt(5, rate);
			
			// sql문 실행 및 결과받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납(pstmt만 반납하기 => 아직 트랜잭션 실행 x)
			close(pstmt); // PreparedStatement 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
		}
		
		// 결과값 return
		return result; // MovieService().insertReview에 결과값 반환하기 (추가 성공 : 1 | 실패 : 0)
		

	}
	
	/**
	 * 5_1. 전체 영화 조회 요청처리하는 jdbc
	 * @return 전체 영화 조회 결과가 담긴 ArrayList<Movie> (텅 빈 리스트 | 조회결과가 담긴 리스트)
	 */
	public ArrayList<Movie> selectMovie(Connection conn){
		
		// select => ResultSet => ArrayList<Movie>
		ArrayList<Movie> movieList = new ArrayList<>();
		
		// PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		// ResultSet 객체 생성
		ResultSet rset = null;
		
		// 실행할 sql문 생성(완성된 sql문)
		String sql = "SELECT * FROM TB_영화";
		
		try {
			// pstmt 객체 완성하기
			pstmt = conn.prepareStatement(sql);
			
			// sql문 실행 및 결과받기
			rset = pstmt.executeQuery();
			
			while (rset.next()) {

				movieList.add(new Movie(rset.getString("MOVIE_NO"), rset.getString("MOVIE_TITLE"),
										rset.getString("MOVIE_AGE"), rset.getDate("OPEN_DATE"), rset.getInt("RATE"),
										rset.getInt("AUDIENCE")));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납(Connection객체 제외 -> 트랜잭션 처리를 안한다해도 객체를 생성한 곳에서 반납하는 것이 좋다)
			close(rset); // ResultSet 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
			close(pstmt); // PreparedStatement 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
		}
		
		// 결과값 return
		return movieList; // MovieService().selectMovie에 결과값 반납(텅 빈 리스트 | 조회결과가 담긴 리스트)

	}
	
	/**
	 * 5_2. 전체 영화 리뷰 조회 요청처리하는 메소드
	 * @return 전체 리뷰 조회 결과가 담긴 ArrayList<Review> (텅 빈 리스트 | 조회결과가 담긴 리스트)
	 */
	public ArrayList<Review> selectReview(Connection conn){
		
		// select => ResultSet => ArrayList<Review>
		ArrayList<Review> reviewList = new ArrayList<>();
		
		// PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		// ResultSet 객체 생성
		ResultSet rset = null;
		
		// 실행할 sql문 생성(완성된 sql문)
		String sql = "SELECT * FROM TB_리뷰";
		
		try {
			// pstmt 객체 완성하기
			pstmt = conn.prepareStatement(sql);
			
			// sql문 실행 및 결과받기
			rset = pstmt.executeQuery();
			
			while (rset.next()) {

				reviewList.add(new Review(rset.getString("USER_ID"), rset.getString("MOVIE_NO"), 
										  rset.getString("MOVIE_TITLE"), rset.getString("CONTENT"), rset.getInt("RATE")));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납(Connection객체 제외 -> 트랜잭션 처리를 안한다해도 객체를 생성한 곳에서 반납하는 것이 좋다)
			close(rset); // ResultSet 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
			close(pstmt); // PreparedStatement 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
		}
		
		// 결과값 return
		return reviewList; // MovieService().selectReview에 결과값 반납(텅 빈 리스트 | 조회결과가 담긴 리스트)


	}
	
	/**
	 * 6. 영화 이름(키워드)에 따른 리뷰를 조회하는 jdbc
	 * @param keyword : 조회하고자하는 영화이름(키워드)
	 * @return : 조회결과가 담겨있는 ArrayList<Review> 
	 */
	public ArrayList<Review> selectKeyReview(Connection conn, String keyword) {
		
		// select(한 행 및 여러행) => ResultSet => ArrayList<Review>
		ArrayList<Review> review = new ArrayList<>(); // 텅 빈 리스트
		
		// PrepraredStatement 객체 생성
		PreparedStatement pstmt = null;
		// ResultSet 객체 생성
		ResultSet rset = null;
		
		// 실행할 sql문 생성
		String sql = "SELECT * FROM TB_리뷰 WHERE MOVIE_TITLE LIKE ?";
		
		try {
			// pstmt 객체 생성 (미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			// 완성된 sql문으로 만들기
			pstmt.setString(1, "%" + keyword + "%");
			
			// rset에 결과값담기
			rset = pstmt.executeQuery();
			
			// sql문 실행 및 결과받기
			while(rset.next()) {
				
				review.add(new Review(rset.getString("USER_ID")
									, rset.getString("MOVIE_NO")
									, rset.getString("MOVIE_TITLE")
									, rset.getString("CONTENT")
									, rset.getInt("RATE")
									));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납
			close(rset); // ResultSet 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
			close(pstmt); // PreparedStatement 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
		}
		
		// 결과값 return 
		return review;	// 조회결과가 담긴 ArrayList<Review>를 MovieServiece().selectKeyReview에 반환

	}
	
	/**
	 * 7. 회원 정보 수정 요청을 처리하는 jdbc
	 * @param userId : 수정을 원하는 회원 아이디
	 * @param pwd ~ phone : 수정할 정보들
	 * @return : 회원 정보가 수정됐는지 확인하기 위한 int값 (수정 성공 : 1 | 실패 : 0)
	 */
	public int updateMember(Connection conn, String userId, String pwd, String name, int age, String phone) {
			
		// update -> int -> 트랜잭션 실행
		int result = 0;
		
		// PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성
		String sql = "UPDATE TB_회원 SET USER_PWD = ?, USER_NAME = ?, AGE = ?, PHONE = ? WHERE USER_ID = ?";
		
		try {
			// pstmt 객체 생성(미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql문으로 변경
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setString(4, phone);
			pstmt.setString(5, userId);
			
			// sql문 실행 및 결과받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납 (Connection 객체 x -> 트랜잭션 실행 x)
			close(pstmt); // PreparedStatement 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
		}

		// 결과값 return
		return result; // 수정 결과가 담긴 결과값을 MovieService().updateMember에 반환

	}
	
	
	/**
	 * 8. 회원 삭제(탈퇴) 요청을 처리하는 jdbc 
	 * @param userId : 삭제(탈퇴)를 원하는 회원아이디
	 * @return : 삭제처리를 확인하기위한 int값 (삭제 성공 : 1 | 실패 : 0)
	 */
	public int deleteMember(Connection conn, String userId) {
		
		// delete => int => 트랜잭션 실행
		int result = 0;
		
		// PreparedStatememt 객체 생성
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 생성
		String sql = "DELETE FROM TB_회원 WHERE USER_ID = ?";
		
		try {
			// pstmt 객체 완성 (미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			// sql문 완성하기
			pstmt.setString(1, userId);
			
			// sql문 생성 및 결과받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납하기
			close(pstmt); // PreparedStatement 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
		}

		// 결과값 return
		return result; // 삭제 결과가 담긴 결과값을 MovieController().deleteMember에 반환

	}
	
	/**
	 * 9. 로그인 요청을 처리하는 jdbc
	 * @param userId : 로그인을 원하는 회원아이디
	 * @param userPwd : 로그인을 원하는 회원비밀번호
	 * @return : 로그인 성공/실패에 따른 값을 담은 Member 객체
	 */
	public Member loginMember(Connection conn, String userId, String userPwd) {
		
		// select(한 행) => ResultSet => Member객체
		Member m = null;
		
		// PreparedStatement 객체 생성
		PreparedStatement pstmt = null;
		// ResultSet 객체 생성
		ResultSet rset = null;
		
		// 실행할 sql문 생성
		String sql = "SELECT USER_NAME FROM TB_회원 WHERE USER_ID = ? AND USER_PWD = ?";
		
		try {
			// pstmt 객체 완성 (미완성된 sql문)
			pstmt = conn.prepareStatement(sql);
			// sql문 완성하기
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			// rset에 결과받기
			rset = pstmt.executeQuery();
			
			// sql문 실행 및 결과받기
			if (rset.next()) {

				m = new Member(); // 기본생성자로 생성

				// user_name에 rset에 들어가있는 값 넣기
				m.setUserName(rset.getString("USER_NAME"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 쓴 객체 반납
			close(rset); // ResultSet 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
			close(pstmt); // PreparedStatement 객체 전달하면서 JDBCTemplate 클래스의 close메소드 실행
		}

		// 결과값 return
		return m; // 조회결과가 담긴 Member 객체를 MovieServiece().loginMember에 반환

	}

}
