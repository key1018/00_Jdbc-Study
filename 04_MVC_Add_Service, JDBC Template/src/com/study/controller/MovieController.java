package com.study.controller;

import java.util.ArrayList;

import com.study.model.vo.Member;
import com.study.model.vo.Movie;
import com.study.model.vo.Review;
import com.study.service.MovieService;
import com.study.view.MovieMenu;

public class MovieController {
	
	// Controller : View를 통해서 사용자가 요청한 기능에 대해 처리하는 담당
	//				해당 메소드로 전달된 데이터 [가공처리한 후] Dao메소드 호출 전달
	//				Dao로부터 반환받은 결과에 따라 성공/실패, 조회결과유무 판단해서
	//				사용자가 보게되는 응답화면 결정 (View 메소드 호출)
	
	/**
	 * 1. 회원 가입 요청처리하는 메소드
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String phone) {
		
		// MovieMenu클래스의 inserMember에서 입력받은 값 Member 객체에 담기
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), phone);
		
		// Member 객체 전달하면서 MovieService().insertMember 메소드 호출하여 결과값 result(int형)에 전달받기
		int result = new MovieService().insertMember(m);
		
		// MemberService에서 return받은 값에 따른 MemberMenu에 있는 결과값을 다르게 출력
		if(result > 0) {
			new MovieMenu().displaySuccess("성공적으로 회원 정보가 추가되었습니다.");
		} else {
			new MovieMenu().displayFail("회원 정보를 추가하는데 실패했습니다.");
		}

	}
	
	/**
	 * 2. 영화 정보 추가 요청처리하는 메소드
	 */
	public void insertMovie(String movieTitle, String movieAge, String openDate) {

		// MovieMenu클래스의 insertMovie에서 입력받은 값을 전달하면서 MovieService().insertMovie 호출하여 결과값 result(int형)에 전달받기
		int result = new MovieService().insertMovie(movieTitle, movieAge, openDate);
		
		// MemberService에서 return받은 값에 따른 MemberMenu에 있는 결과값을 다르게 출력
		if(result > 0) {
			new MovieMenu().displaySuccess("성공적으로 영화 정보가 추가되었습니다.");
		} else {
			new MovieMenu().displayFail("영화 정보를 추가하는데 실패했습니다.");
		}
	}
	
	/**
	 * 3. 예매 내역 추가 요청처리하는 메소드
	 */
	public void insertReserve(String movieNo, String audience, String confirmed) {
		
		// MovieMenu클래스의 insertReserve에서 입력받은 값을 전달하면서 MovieService().insertReserve 호출하여 결과값 result(int형)에 전달받기
		int result = new MovieService().insertReserve(movieNo, audience, confirmed);
		
		// MemberService에서 return받은 값에 따른 MemberMenu에 있는 결과값을 다르게 출력
		if(result > 0) {
			new MovieMenu().displaySuccess("성공적으로 예매 정보가 추가되었습니다.");
		} else {
			new MovieMenu().displayFail("예매 정보를 추가하는데 실패했습니다.");
		}

	}
	
	/**
	 * 4. 영화 리뷰 추가 요청처리하는 메소드
	 */
	public void insertReview(String userId, String movieNo, String movieTitle, String content, String rate) {

		// MovieMenu클래스의 insertReview에서 입력받은 값을 전달하면서 MovieService().insertReview 호출하여 결과값 result(int형)에 전달받기
		int result = new MovieService().insertReview(userId, movieNo, movieTitle, content, rate);
		
		// MemberService에서 return받은 값에 따른 MemberMenu에 있는 결과값을 다르게 출력
		if(result > 0) {
			new MovieMenu().displaySuccess("성공적으로 리뷰 정보가 추가되었습니다.");
		} else {
			new MovieMenu().displayFail("리뷰 정보를 추가하는데 실패했습니다.");
		}
		
		
	}

	/**
	 * 5_1. 전체 영화 조회 요청처리하는 메소드
	 */
	public void selectMovie() {

		// ArrayList<Movie>에서 조회되는 값들을 MovieService().selectMovie 호출하면서 결과값 전달받기
		ArrayList<Movie> movieList = new MovieService().selectMovie();

		// MemberService에서 return받은 값에 따른 MemberMenu에 있는 결과값을 다르게 출력
		if (movieList.isEmpty()) {
			new MovieMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new MovieMenu().displaySelectMovie(movieList);
		}

	}
	
	/**
	 * 5_2. 전체 영화 리뷰 조회 요청처리하는 메소드
	 */
	public void selectReview() {

		// ArrayList<Review>에서 조회되는 값들을 MovieService().selectReview 호출하면서 결과값 전달받기
		ArrayList<Review> reviewList = new MovieService().selectReview();

		// MemberService에서 return받은 값에 따른 MemberMenu에 있는 결과값을 다르게 출력
		if (reviewList.isEmpty()) {
			new MovieMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new MovieMenu().displaySelectReview(reviewList);
		}

	}
	
	/**
	 * 6. 영화 이름(키워드)에 따른 리뷰 조회를 요청처리하는 메소드
	 * @param keyword : 조회하고자하는 영화이름(키워드)
	 */
	public void selectKeyReview(String keyword) {

		// keyword값들을 전달하면서 MovieService().selectKeyReview호출하면서 전달받은 값들을
		// ArrayList<Review>에 전달받기
		ArrayList<Review> review = new MovieService().selectKeyReview(keyword);

		if (review.isEmpty()) {
			new MovieMenu().displayNoData(keyword + "와 관련된 영화 리뷰 결과가 없습니다.");
		} else {
			new MovieMenu().displayKeyword(review);
		}

	}
	
	/**
	 * 7. 회원 정보 수정 요청을 처리하는 메소드
	 */
	public void updateMember(String userId, String pwd, String name, String age, String phone) {

		// 입력받은 값들을 전달하면서 MovieService().updateMember호출하고, 그에따라 전달받은 값들을
		// int result에 전달받기
		int result = new MovieService().updateMember(userId, pwd, name, Integer.parseInt(age), phone);
		
		if(result > 0) {
			new MovieMenu().displayNoData("회원 정보가 수정되었습니다.");
		} else {
			new MovieMenu().displayFail("회원 정보를 수정하는데 실패했습니다.");
		}


	}
	
	/**
	 * 8. 회원 삭제(탈퇴) 요청을 처리하는 메소드
	 */
	public void deleteMember(String userId) {
		
		// 입력받은 값(userId)을 전달하면서 MovieService().deleteMember호출하고, 그에따라 전달받은 값들을
		// int result에 전달받기
		int result = new MovieService().deleteMember(userId);

		if (result > 0) {
			new MovieMenu().displayNoData("회원 정보가 삭제되었습니다.");
		} else {
			new MovieMenu().displayFail("회원 정보를 삭제하는데 실패했습니다.");
		}

	}
	
	/**
	 * 9. 로그인 요청을 처리하는 메소드
	 */
	public void loginMember(String userId, String userPwd) {
		
		// 입력받은 값(userId, userPwd)을 전달하면서 MovieService().loginMember호출하고, 그에따라 전달받은 값들을
		// Member 객체에 전달받기
		Member m = new MovieService().loginMember(userId, userPwd);

		if (m == null) {
			new MovieMenu().displayFail("로그인에 실패하셨습니다.");
		} else {
			new MovieMenu().displaySuccess(m.getUserName() + "님 반갑습니다^^");
		}

	}

}
