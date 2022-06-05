package com.study2.member.controller;

import java.util.ArrayList;

import com.study2.member.model.dao.MovieDao;
import com.study2.member.model.vo.Member;
import com.study2.member.model.vo.Movie;
import com.study2.member.model.vo.Review;
import com.study2.member.view.MovieMenu;

public class MovieController {
	
	// Controller : View를 통해서 사용자가 요청한 기능에 대해 처리하는 담당
	//				해당 메소드로 전달된 데이터 [가공처리한 후] Dao메소드 호출 전달
	//				Dao로부터 반환받은 결과에 따라 성공/실패, 조회결과유무 판단해서
	//				사용자가 보게되는 응답화면 결정 (View 메소드 호출)
	
	/**
	 * 1. 회원 가입 요청처리하는 메소드
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String phone) {

		int result = new MovieDao().insertMember(userId, userPwd, userName, gender, Integer.parseInt(age), phone);

		if (result > 0) {
			new MovieMenu().displaySuccess("회원가입에 성공하였습니다.");
		} else {
			new MovieMenu().displayFail("회원가입에 실패하였습니다.");
		}

	}
	
	/**
	 * 2. 영화 정보 추가 요청처리하는 메소드
	 */
	public void insertMovie(String movieTitle, String movieAge, String openDate) {
		
		int result = new MovieDao().insertMovie(movieTitle, movieAge, openDate);
		
		if(result > 0) {
			new MovieMenu().displaySuccess("영화정보를 등록하는데 성공했습니다.");
		} else {
			new MovieMenu().displayFail("영화정보를 등록하는데 실패했습니다.");
		}
		
	}
	
	/**
	 * 3. 예매 내역 추가 요청처리하는 메소드
	 */
	public void insertReserve(String movieNo, String audience, String confirmed) {
		
		int result = new MovieDao().insertReserve(movieNo, Integer.parseInt(audience), confirmed);
		
		if(result > 0) {
			new MovieMenu().displaySuccess("성공적으로 예매 정보가 등록되었습니다.");
		} else {
			new MovieMenu().displayFail("예매 정보를 등록하는데 실패했습니다.");
		}

	}
	
	/**
	 * 4. 영화 리뷰 추가 요청처리하는 메소드
	 */
	public void insertReview(String userId, String movieNo, String movieTitle, String content, String rate) {
		
		int result = new MovieDao().insertReview(userId, movieNo, movieTitle, content, Integer.parseInt(rate));
		
		if(result > 0) {
			new MovieMenu().displaySuccess("성공적으로 리뷰가 등록되었습니다.");
		} else {
			new MovieMenu().displayFail("리뷰를 등록하는데 실패했습니다.");
		}
	}

	/**
	 * 5_1. 전체 영화 조회 요청처리하는 메소드
	 */
	public void selectMovie() {
		
		ArrayList<Movie> list = new MovieDao().selectMovie();
		
		if(list.isEmpty()) {
			new MovieMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new MovieMenu().displaySelectMovie(list);
		}
		
	}
	
	/**
	 * 5_2. 전체 영화 리뷰 조회 요청처리하는 메소드
	 */
	public void selectReview() {
		
		ArrayList<Review> list = new MovieDao().selectReview();
		
		if(list.isEmpty()) {
			new MovieMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new MovieMenu().displaySelectReview(list);
		}
		
	}
	
	/**
	 * 6. 영화 이름(키워드)에 따른 리뷰 조회를 요청처리하는 메소드
	 * @param keyword : 조회하고자하는 영화이름(키워드)
	 */
	public void selectKeyReview(String keyword) {
		
		ArrayList<Review> list = new MovieDao().selectKeyReview(keyword);
		
		if(list.isEmpty()) {
			new MovieMenu().displayNoData(keyword + "에 따른 조회결과가 없습니다.");
		} else {
			new MovieMenu().displayKeyword(list);
		}

	}
	
	/**
	 * 7. 회원 정보 수정 요청을 처리하는 메소드
	 */
	public void updateMember(String userId, String pwd, String name, String age, String phone) {

		int result = new MovieDao().updateMember(userId, pwd, name, Integer.parseInt(age), phone);

		if (result > 0) {
			new MovieMenu().displaySuccess("회원 정보를 수정하는데 성공했습니다.");
		} else {
			new MovieMenu().displayFail("회원 정보를 수정하는데 실패했습니다.");
		}

	}
	
	/**
	 * 8. 회원 삭제(탈퇴) 요청을 처리하는 메소드
	 */
	public void deleteMember(String userId) {

		int result = new MovieDao().deleteMember(userId);

		if (result > 0) {
			new MovieMenu().displaySuccess("회원 정보를 삭제하는데 성공했습니다.");
		} else {
			new MovieMenu().displayFail("회원 정보를 삭제하는데 실패했습니다.");
		}

	}
	
	/**
	 * 9. 로그인 요청을 처리하는 메소드
	 */
	public void loginMember(String userId, String userPwd) {

		Member m = new MovieDao().loginMember(userId, userPwd);

		if (m == null) {
			new MovieMenu().displayFail("로그인에 실패했습니다.");
		} else {
			new MovieMenu().displaySuccess(m.getUserName() + "님 환영합니다.");
		}

	}

}
