package com.study.service;

// JDBCTemplate 클래스에 있는 모든 메소드를 JDBCTemplate를 불러서 호출하지 않고
// 메소드명만으로 호출할 수 있게하는 import문 
import static com.study.common.JDBCTemplate.close;
import static com.study.common.JDBCTemplate.commit;
import static com.study.common.JDBCTemplate.getConnection;
import static com.study.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.study.model.dao.MovieDao;
import com.study.model.vo.Member;
import com.study.model.vo.Movie;
import com.study.model.vo.Review;

// 여러개의 Dao클래스를 처리할 수 있는 비즈니스 로직이 담긴 클래스
public class MovieService {
	
	/**
	 * 1. 회원 가입 요청처리하는 메소드 
	 * @return : 회원 정보 추가 결과가 담겨있는 result(int형) => 성공 : 1 | 실패 : 0
	 */
	public int insertMember(Member m) {
		
		// 1,2) jdbc driver 및 Connection 객체 생성
		Connection conn = getConnection();
		
		// 3,4,5,6) Connection과 Member 객체 MovieDao().insertMember에 값 전달하면서 
		// result(int형)에 결과값 전달받기 (값 주고받기)
		int result = new MovieDao().insertMember(conn, m);
		
		// 트랜잭션 실행
		if(result > 0) {
			commit(conn); // Connection 객체 전달하면서 JDBCTemplate 클래스의 commit메소드 실행
		} else {
			rollback(conn); // Connection 객체 전달하면서 JDBCTemplate 클래스의  rollback메소드 실행
		}
		
		// Connection 객체 반납
		close(conn); // Connection 객체 전달하면서  JDBCTemplate 클래스의 close메소드 실행
		
		// 결과값 return (MovieController().insertMember에 결과값 반환) (추가 성공 : 1 | 실패 : 0)
		return result; 

	}
	
	/**
	 * 2. 영화 정보 추가 요청처리하는 로직(service)
	 * @return : 영화 정보 추가 결과가 담겨있는 result(int형) => 성공 : 1 | 실패 : 0
	 */
	public int insertMovie(String movieTitle, String movieAge, String openDate) {

		// 1, 2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();

		// 3,4,5,6) 전달받은 값 및 Connection객체 MovieDao().insertMovie에 전달하면서 결과값
		// insert(int형)에 전달받기 (값 주고받기)
		int result = new MovieDao().insertMovie(conn, movieTitle, movieAge, openDate);

		// 트랜잭션 실행
		if (result > 0) {
			commit(conn); // Connection 객체 전달하면서 JDBCTemplate 클래스의 commit메소드 실행
		} else {
			rollback(conn); // Connection 객체 전달하면서 JDBCTemplate 클래스의 rollback메소드 실행
		}
		
		// 트랜잭션을 실행했으므로 Connecion객체 반환하기
		close(conn); // Connection 객체 전달하면서  JDBCTemplate 클래스의 close메소드 실행

		// 결과값 return
		return result; // MovieController().insertMember에 결과값 반환 (추가 성공 : 1 | 실패 : 0)

	}

	/**
	 * 3. 예매 내역 추가 요청처리하는 로직(service)
	 * @return : 예매 내역 추가 결과가 담겨있는 result(int형) => 성공 : 1 | 실패 : 0
	 */
	public int insertReserve(String movieNo, String audience, String confirmed) {

		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();

		// 3,4,5,6) 전달받은 값 및 Connection객체를 MovieDao().insertReserve에 전달하면서 result(int형)에
		// 결과값 전달받기 (값 주고받기)
		int result = new MovieDao().insertReserve(conn, movieNo, Integer.parseInt(audience), confirmed);

		// 트랜잭션 실행
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		// 트랜잭션 실행 후 Connection 객체 반납
		close(conn);

		// 결과값 return
		return result; // MovieController().insertReserve에 결과값 반환 (추가 성공 : 1 | 실패 : 0)

	}

	/**
	 * 4. 영화 리뷰 추가 요청처리하는 로직(service)
	 * @return : 리뷰 추가 결과가 담겨있는 result(int형) => 성공 : 1 | 실패 : 0
	 */
	public int insertReview(String userId, String movieNo, String movieTitle, String content, String rate) {

		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();

		// 3,4,5,6) 전달받은 값 및 Connection객체를 MovieDao().insertReview에 전달하면서 result(int형)에
		// 결과값 전달받기 (값 주고받기)
		int result = new MovieDao().insertReview(conn, userId, movieNo, movieTitle, content, Integer.parseInt(rate));

		// 트랜잭션 실행
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		// 트랜잭션 실행 후 Connection 객체 반납
		close(conn);

		// 결과값 return
		return result; // MovieController().insertReview에 결과값 반환 (추가 성공 : 1 | 실패 : 0)

	}

	/**
	 * 5_1. 전체 영화 조회 요청처리하는 로직(service)
	 * @return : 조회 결과가 담겨있는 ArrayList<Movie> 객체
	 */
	public ArrayList<Movie> selectMovie() {

		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();

		// 3,4,5,6) Connection객체를 전달하면서 MovieDao().selectMovie에서 조회되는 값들을
		// ArrayList<Movie>에 전달받기 (값 주고받기)
		ArrayList<Movie> movieList = new MovieDao().selectMovie(conn);

		// Connection 객체 반납
		close(conn);

		// 결과값 return
		return movieList; // MovieMenu().selectMovie에 결과값 반납(텅 빈 리스트 | 조회결과가 담긴 리스트)

	}
	
	/**
	 * 5_2. 전체 영화 리뷰 조회 요청처리하는 로직(service)
	 * @return : 조회 결과가 담겨있는 ArrayList<Review> 객체
	 */
	public ArrayList<Review> selectReview() {

		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();

		// 3,4,5,6) Connection객체를 전달하면서 MovieDao().selectReview에서 조회되는 값들을
		// ArrayList<Movie>에 전달받기 (값 주고받기)
		ArrayList<Review> movieList = new MovieDao().selectReview(conn);

		// Connection 객체 반납
		close(conn);

		// 결과값 return
		return movieList; // MovieMenu().selectReview에 결과값 반납(텅 빈 리스트 | 조회결과가 담긴 리스트)

	}
	
	/**
	 * 6. 영화 이름(키워드)에 따른 리뷰 조회를 요청처리하는 로직(service)
	 * @return : 이름(키워드)에 따른 조회 결과가 담겨있는 ArrayList<Review> 객체
	 */
	public ArrayList<Review> selectKeyReview(String keyword) {
		
		// 1,2) jdbc driver 등록 및 Connection 객체 등록
		Connection conn = getConnection();

		// 3,4,5,6) Connection 객체 및 keyword값 MovieDao().selectKeyReview에 전달하면서 조회되는 값들을
		// ArrayList<Review> 객체에 전달받기 (값 주고받기)
		ArrayList<Review> review = new MovieDao().selectKeyReview(conn, keyword);

		// Connection 객체 반납
		close(conn);

		// 결과값 return
		return review; // MovieDao().selectKeyReview에서 전달받은 값을 MovieController().selectKeyReview에 결과값 반환

	}

	/**
	 * 7. 회원 정보 수정 요청을 처리하는 로직(service) 
	 * @return : 수정 결과가 담겨있는 result(int형)
	 */
	public int updateMember(String userId, String pwd, String name, int age, String phone) {
		
		// 1,2) jdbc driver 및 Connection 객체 생성
		Connection conn = getConnection();
		
		// 3,4,5,6) 전달받은 매개변수들 및 Connection객체들을 MovieDao().updateMember에 전달하면서 전달받은 값을
		// int result에 전달받기 (값 주고받기)
		int result = new MovieDao().updateMember(conn, userId, pwd, name, age, phone);
		
		// 트랜잭션 실행
		if(result > 0) {
			commit(conn); // Connection 객체 전달하면서 JDBCTemplate클래스의 commit메소드 실행
		} else {
			rollback(conn); // Connection 객체 전달하면서 JDBCTemplate클래스의 rollback메소드 실행
		}
		
		// 결과값 return
		return result; // MovieDao().updateMember에서 전달받은 값을 MovieController().updateMember에 결과값 반환  
		
	}
	
	/**
	 * 8. 회원 삭제(탈퇴) 요청을 처리하는 로직(service)
	 * @return : 삭제 결과가 담겨있는 result(int형)
	 */
	public int deleteMember(String userId) {
		
		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();
		
		// 3,4,5,6) 전달받은 userId 및 Connection 객체를 MovieDao().deleteMember에 전달하면서 그에따라 전달받은 값을
		// int result에 전달받기 (값 주고받기)
		int result = new MovieDao().deleteMember(conn, userId);
		
		// 트랜잭션 실행
		if(result > 0) {
			commit(conn); // Connection 객체 전달하면서 JDBCTemplate클래스의 commit메소드 실행
		} else {
			rollback(conn); // Connection 객체 전달하면서 JDBCTemplate클래스의 rollback메소드 실행
		}
		
		// Connection 객체 반납
		close(conn);
		
		// 결과값 return
		return result;	// MovieDao().deleteMember에서 전달받은 값을 MovieController().deleteMember에 결과값 반환  

	}
	
	/**
	 * 9. 로그인 요청을 처리하는 로직(service)
	 * @return : 로그인 결과가 담겨있는 Member 객체
	 */
	public Member loginMember(String userId, String userPwd) {
		
		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();
		
		// 3,4,5,6) 전달받은 userId, userPwd 및 Connection 객체를 MovieDao().loginMember에 전달하면서 그에따라 전달받은 값을
		// Member 객체에 전달받기 (값 주고받기)
		Member m = new MovieDao().loginMember(conn, userId, userPwd);
		
		// Connection 객체 반납
		close(conn); // Connection 객체 전달하면서 JDBCTemplate클래스의 commit메소드 실행
		
		// 결과값 return
		return m;	// MovieDao().loginMember에서 받은 조회결과가 담긴 Member객체를 MovieController().loginMember에 반환

	}

}
