package com.study.service;

import static com.study.common.JDBCTemplate.close;
import static com.study.common.JDBCTemplate.commit;
import static com.study.common.JDBCTemplate.getConnection;
import static com.study.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.study.model.dao.MovieDao;
import com.study.model.vo.Member;
import com.study.model.vo.Movie;
import com.study.model.vo.Reserve;
import com.study.model.vo.Review;

public class MovieService {
	
	public int insertMember(Member m) {
		
		Connection conn = getConnection();
		
		int result = new MovieDao().insertMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int insertMovie(String movieTitle, String movieAge, String openDate) {
		
		Connection conn = getConnection();
		
		int result = new MovieDao().insertMovie(conn, movieTitle, movieAge, openDate);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int insertReserve(Reserve r) {
		
		Connection conn = getConnection();
		
		int result = new MovieDao().insertReserve(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
		
	}
	
	public int insertReview(Review re) {
		
		Connection conn = getConnection();
		
		int result = new MovieDao().insertReview(conn, re);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;	
		
	}
	
	public ArrayList<Movie> selectMovie(){
		
		Connection conn = getConnection();
		
		ArrayList<Movie> movie = new MovieDao().selectMovie(conn);
		
		close(conn);
		
		return movie;
		
	}
	
	public ArrayList<Review> selectReview(){
		
		Connection conn = getConnection();
		
		ArrayList<Review> review = new MovieDao().selectReview(conn);
		
		close(conn);
		
		return review;
	
	}
	
	public ArrayList<Movie> selectKeyword(String keyword) {
		
		Connection conn = getConnection();
		
		ArrayList<Movie> list = new MovieDao().selectKeyword(conn, keyword);
	
		close(conn);

		return list;

	}
	
	public int updateMember(String userPwd, String phone, String userId) {
		
		Connection conn=getConnection();
		
		int result = new MovieDao().updateMember(conn, userPwd, phone, userId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int deleteMember(String userId) {
		
		Connection conn = getConnection();
		
		int result = new MovieDao().deleteMember(conn, userId);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}
