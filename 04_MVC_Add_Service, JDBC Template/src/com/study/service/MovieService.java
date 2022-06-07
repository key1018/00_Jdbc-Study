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

// JDBC Template 클래스에서 작성된 메소드들 실행하는 클래스
public class MovieService {
	
	// 회원 추가
	public int insertMember(Member m) {
		
		// 1,2) jdbc driver 및 Connection 객체 생성
		Connection conn = getConnection();
		
		// 3,4,5,6) Connection과 Member 객체 MovieDao().insertMember에 값 전달하면서 result(int형)에 결과값 전달받기
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

	public int insertMovie(String movieTitle, String movieAge, String openDate) {

		// 1, 2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();

		// 3,4,5,6) 전달받은 값 및 Connection객체 MovieDao().insertMovie에 전달하면서 결과값
		// insert(int형)에 전달받기
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

	public int insertReserve(String movieNo, String audience, String confirmed) {
		
		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();
		
		// 3,4,5,6) 전달받은 값 및 Connection객체를 MovieDao().insertReserve에 전달하면서 result(int형)에 결과값 전달받기
		int result = new MovieDao().insertReserve(conn, movieNo, Integer.parseInt(audience), confirmed);
		
		// 트랜잭션 실행
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		// 트랜잭션 실행 후 Connection 객체 반납
		close(conn);
		
		// 결과값 return
		return result; // MovieController().insertReserve에 결과값 반환 (추가 성공 : 1 | 실패 : 0)
		
	}

	public int insertReview(String userId, String movieNo, String movieTitle, String content, String rate) {

		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();

		// 3,4,5,6) 전달받은 값 및 Connection객체를 MovieDao().insertReview에 전달하면서 result(int형)에
		// 결과값 전달받기
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

	public ArrayList<Movie> selectMovie() {

		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();

		// 3,4,5,6) Connection객체를 전달하면서 MovieDao().selectMovie에서 조회되는 값들을
		// ArrayList<Movie>에 전달받기
		ArrayList<Movie> movieList = new MovieDao().selectMovie(conn);

		// Connection 객체 반납
		close(conn);

		// 결과값 return
		return movieList; // MovieMenu().selectMovie에 결과값 반납(텅 빈 리스트 | 조회결과가 담긴 리스트)

	}

	public ArrayList<Review> selectReview() {

		// 1,2) jdbc driver 등록 및 Connection 객체 생성
		Connection conn = getConnection();

		// 3,4,5,6) Connection객체를 전달하면서 MovieDao().selectReview에서 조회되는 값들을
		// ArrayList<Movie>에 전달받기
		ArrayList<Review> movieList = new MovieDao().selectReview(conn);

		// Connection 객체 반납
		close(conn);

		// 결과값 return
		return movieList; // MovieMenu().selectReview에 결과값 반납(텅 빈 리스트 | 조회결과가 담긴 리스트)

	}
	
}
