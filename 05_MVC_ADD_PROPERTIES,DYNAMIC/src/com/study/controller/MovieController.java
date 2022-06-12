package com.study.controller;

import java.util.ArrayList;

import com.study.model.vo.Member;
import com.study.model.vo.Movie;
import com.study.model.vo.Reserve;
import com.study.model.vo.Review;
import com.study.service.MovieService;
import com.study.view.MovieMenu;

public class MovieController {
	
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String phone) {
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), phone);
		
		int result = new MovieService().insertMember(m);
		
		if(result > 0) {
			new MovieMenu().successInsertM(m);
		} else {
			new MovieMenu().displayFail("회원 가입에 실패했습니다.");
		}
		
		
	}
	
	public void insertMovie(String movieTitle, String movieAge, String openDate) {
		
		int result = new MovieService().insertMovie(movieTitle, movieAge, openDate);
		
			if(result > 0) {
				new MovieMenu().displaySuccess("성공적으로 영화 정보를 추가했습니다.");
			} else {
				new MovieMenu().displayFail("영화 정보를 추가하는데 실패했습니다.");
			}

		}

	
	public void insertReserve(String movieNo, String audience, String confirmed) {
		
		Reserve r = new Reserve(movieNo, Integer.parseInt(audience), confirmed);
		
		int result = new MovieService().insertReserve(r);
		
		if(result > 0) {
			new MovieMenu().successInsertR(r);
		} else {
			new MovieMenu().displayFail("예매 정보를 추가하는데 실패했습니다.");
		}
		
		
	}
	
	public void insertReview(String userId, String movieNo, String movieTitle, String content, String rate) {
		
		Review re = new Review(userId, movieNo, movieTitle, content, Integer.parseInt(rate));
		
		int result = new MovieService().insertReview(re);
		
		if(result > 0) {
			new MovieMenu().successInsertRe(re);
		} else {
			new MovieMenu().displayFail("리뷰 정보를 추가하는데 실패했습니다.");
		}
		
	}
	
	public void selectMovie() {
		
		ArrayList<Movie> movie = new MovieService().selectMovie();
		
		if(movie.isEmpty()) {
			new MovieMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new MovieMenu().displayMovie(movie);
		}

	}
	
	public void selectReview() {
		
		ArrayList<Review> review = new MovieService().selectReview();
		
		if(review.isEmpty()) {
			new MovieMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new MovieMenu().displayReview(review);
		}

	}
	
	public void selectKeyword(String keyword) {
		
		ArrayList<Movie> list = new MovieService().selectKeyword(keyword);
	
		if(list.isEmpty()) {
			new MovieMenu().displayNoData(keyword + "에 대한 조회 결과가 없습니다.");
		} else {
			new MovieMenu().displayKeyword(keyword + "에 대한 조회 결과가 입니다.\n"
											, list);
		}
		
	}
	
	public void updateMember(String userPwd, String phone, String userId) {

		int result = new MovieService().updateMember(userPwd, phone, userId);

		if (result > 0) {
			new MovieMenu().displaySuccess("회원 정보를 수정했습니다.");
		} else {
			new MovieMenu().displayFail(userId + "에 대한 회원정보를 찾지못했습니다.");
		}

	}

	public void deleteMember(String userId) {
		
		int result = new MovieService().deleteMember(userId);
		
		if (result > 0) {
			new MovieMenu().displaySuccess("회원 정보를 삭제했습니다.");
		} else {
			new MovieMenu().displayFail(userId + "에 대한 회원정보를 찾지못했습니다.");
		}

	}

}

