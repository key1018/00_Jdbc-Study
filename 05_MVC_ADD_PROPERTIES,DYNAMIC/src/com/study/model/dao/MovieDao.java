package com.study.model.dao;

import static com.study.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.study.model.vo.Member;
import com.study.model.vo.Movie;
import com.study.model.vo.Reserve;
import com.study.model.vo.Review;

public class MovieDao {
	
	Properties prop = new Properties();
	
	public MovieDao() {
		
		try {
			prop.loadFromXML(new FileInputStream("resources/Movie.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int insertMember(Connection conn, Member m) {
		
		// insert => int => 트랜잭션 실행
		
		int result = 0;
		
		PreparedStatement pstmt= null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getPhone());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int insertMovie(Connection conn, String movieTitle, String movieAge, String openDate) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMovie");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, movieTitle);
			pstmt.setString(2, movieAge);
			pstmt.setString(3, openDate);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}
	
	public int insertReserve(Connection conn, Reserve r) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReserve");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getMovieNo());
			pstmt.setInt(2, r.getAudience());
			pstmt.setString(3, r.getConfirmed());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;

	}
	
	public int insertReview(Connection conn, Review re) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReview");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, re.getUserId());
			pstmt.setString(2, re.getMovieNo());
			pstmt.setString(3, re.getMovieTitle());
			pstmt.setString(4, re.getContent());
			pstmt.setInt(5, re.getRate());
			
			result=pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public ArrayList<Movie> selectMovie(Connection conn){
		
		ArrayList<Movie> movie = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMovie");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				movie.add(new Movie(rset.getString("MOVIE_NO"), rset.getString("MOVIE_TITLE")
							      , rset.getString("MOVIE_AGE"), rset.getDate("open_date")
							      , rset.getInt("rate"), rset.getInt("audience")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return movie;
		
	}
	
	public ArrayList<Review> selectReview(Connection conn){
		
		ArrayList<Review> review = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReview");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				review.add(new Review(rset.getString("user_id"),
									  rset.getString("movie_no"),
									  rset.getString("movie_title"),
									  rset.getString("content"),
									  rset.getInt("rate")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return review;
	}
	
	public ArrayList<Movie> selectKeyword(Connection conn, String keyword) {
		
		ArrayList<Movie> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectKeyword");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
		
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				
			Movie m = new Movie();
			
			m.setMovieNo(rset.getString("movie_no"));
			m.setMovieTitle(rset.getString("movie_title"));
			m.setMovieAge(rset.getString("movie_age"));
			m.setOpenDate(rset.getDate("open_date"));
				
			list.add(m);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}

	public int updateMember(Connection conn, String userPwd, String phone, String userId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, userPwd);
			pstmt.setString(2, phone);
			pstmt.setString(3, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

	}
	
	public int deleteMember(Connection conn, String userId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
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
			close(pstmt);
		}
		
		return result;
	}

}
