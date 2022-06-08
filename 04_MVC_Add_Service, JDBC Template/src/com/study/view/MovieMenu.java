package com.study.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.study.controller.MovieController;
import com.study.model.vo.Member;
import com.study.model.vo.Movie;
import com.study.model.vo.Review;

public class MovieMenu {

	// View : 사용자가 보게될 화면으로 입력(Scanner) 및 출력(print) 제공
	
	// Scanner 객체 생성
	private Scanner sc = new Scanner(System.in);
	// Member 객체 생성
	Member m = new Member();	
	
	// MemberController 클래스 호출
	MovieController mc = new MovieController();
	
	// 사용자가 보게될 회원 정보 관리 화면(메인화면)
	// MemberService 호출하여 값 전달받기
	public void MovieMenu() {
		
		while(true) { // 반복적으로 호출
			
			System.out.println("\n=== 회원 관리 프로그램 ===");
			System.out.println("1. 회원 추가");
			System.out.println("2. 영화 정보 추가");
			System.out.println("3. 영화 예매(확정/취소) 내역 추가");
			System.out.println("4. 영화 리뷰 추가");
			System.out.println("5. 전체 영화/리뷰 정보 조회");
			System.out.println("6. 영화 이름(키워드)에 따른 리뷰 검색");
			System.out.println("7. 회원 정보 수정");
			System.out.println("8. 회원 탈퇴");
			System.out.println("9. 로그인");
			System.out.println("0. 프로그램 종료");
			System.out.print(">> 메뉴를 선택하세요 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : insertMember(); break;
			case 2 : insertMovie(); break;
			case 3 : insertReserve(); break;
			case 4 : insertReview(); break;
			case 5 : selectAll(); break;
			case 6 : selectKeyReview(); break;
			case 7 : updateMember(); break;
			case 8 : deleteMember(); break;
			case 9 : loginMember(); break;
			case 0 : System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다."); return;
			default : System.out.println("메뉴를 다시 선택해주세요.");
			} // switch문 끝

		}// while문 끝

	} // MemberMenu 끝
	
	//------------------------서비스 요청 화면---------------------
	
	// 1. 회원 추가시 사용자에게 보여지는 화면(서브화면)
	public void insertMember() {
		
		System.out.println("\n=== 회원 가입 ===");
		
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		System.out.print("성별(M/F) : ");
		String gender =sc.nextLine().toUpperCase();
		System.out.print("나이 : ");
		String age = sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		mc.insertMember(userId, userPwd, userName, gender, age, phone);

	}
	
	// 2. 영화 추가시 사용자에게 보여지는 화면(서브화면)
	public void insertMovie() {
		
		System.out.println("\n=== 영화 정보 추가 ===");
		
		System.out.print("영화제목 : ");
		String movieTitle = sc.nextLine();
		System.out.print("영화등급 : ");
		String movieAge = sc.nextLine();
		System.out.print("개봉일 : ");
		String openDate = sc.nextLine();
		
		mc.insertMovie(movieTitle, movieAge, openDate);

	}
	
	// 3. 영화 예매 요청시 사용자에게 보여지는 화면(서브화면)
	public void insertReserve() {
		
		System.out.println("\n=== 영화 예매 ===");
		
		System.out.print("영화번호 : ");
		String movieNo = sc.nextLine();
		System.out.print("예매인원 : ");
		String audience = sc.nextLine();
		System.out.print("예매확인(확정/취소) : ");
		String confirmed = sc.nextLine();
		
		mc.insertReserve(movieNo, audience, confirmed);
	}
	
	// 4. 영화 리뷰 추가 요청시 사용자에게 보여지는 화면(서브화면)
	public void insertReview() {
		
		System.out.println("\n=== 영화 리뷰 추가 ===");
		
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("영화번호 : ");
		String movieNo = sc.nextLine();
		System.out.print("영화제목 : ");
		String movieTitle = sc.nextLine();
		System.out.print("리뷰내용 : ");
		String content = sc.nextLine();
		System.out.print("평점 : ");
		String rate = sc.nextLine();
		
		mc.insertReview(userId, movieNo, movieTitle, content, rate);

	}
	
	// 5. 영화 정보 / 리뷰 조회 요청시 사용자에게 보여지는 화면(서브화면)
	public void selectAll() {
		
		System.out.println("\n=== 조회 서비스 ===");
		
		System.out.println("1) 전체 영화 조회");
		System.out.println("2) 전체 리뷰 조회");

		System.out.print(">> 메뉴 선택 : ");
		int num = sc.nextInt();
		
		if (num == 1) {
			mc.selectMovie();
		} else if (num == 2){
			mc.selectReview();
		} else {
			System.out.println("메뉴를 다시 선택해주세요.");
			selectAll();
		}

	}
	
	// 6. 영화 이름(키워드)를 조회하여 그에 맞는 리뷰가 사용자에게 보여지는 화면(서브화면)
	public void selectKeyReview() {

		System.out.print("\n영화제목(키워드 입력 가능) : ");
		String keyword = sc.nextLine();

		mc.selectKeyReview(keyword);

	}

	// 7. 회원 정보 수정 요청시 사용자에게 보여지는 화면(서브화면)
	public void updateMember() {
		
		System.out.println("\n=== 회원 정보 수정 ===");
		System.out.print("변경을 원하는 아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("나이 : ");
		String age = sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		mc.updateMember(userId, pwd, name, age, phone);
		
	}
	
	// 8. 회원 삭제(탈퇴) 요청시 사용자에게 보여지는 화면(서브화면)
	public void deleteMember() {
		
		
		System.out.println("\n=== 회원 정보 삭제(탈퇴) ===");
		
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		
		mc.deleteMember(userId);
		
	}
	
	// 9. 회원 로그인 요청시 사용자에게 보여지는 화면(서브화면)
	public void loginMember() {
		
		System.out.println("\n==== 로그인 ====");

		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();

		mc.loginMember(userId, userPwd);
	}

	//----------------------- 서비스 응답 화면 ----------------------------
	
	// 사용자의 요청이 성공하면 보여지는 화면(서브화면)
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 성공 : " + message);
	}
	
	// 사용자의 요청이 실패하면 보여지는 화면(서브화면)
	public void displayFail(String message) {
		System.out.println("서비스 요청 실패 : " + message);
	}
	
	// 조회 결과가 없는 경우 사용자에게 보여지는 화면(서브화면)
	public void displayNoData(String message) {
		System.out.println(message);
	}
	
	// 전체 영화의 조회 결과가 있는 경우 사용자에게 보여지는 화면(서브화면)
	public void displaySelectMovie(ArrayList<Movie> list) {
		System.out.println("\n=== 전체 영화 리스트 ===");
		for(Movie m : list) {
			System.out.println(m);
		}
	}
	
	// 전체 영화의 리뷰에 대한 조회 결과가 있는 경우 사용자에게 보여지는 화면(서브화면)
	public void displaySelectReview(ArrayList<Review> list) {
		System.out.println("\n=== 전체 리뷰 리스트 ===");
		for(Review r : list) {
			System.out.println(r);
		}
	}
	
	// 영화이름(키워드)을 입력했을 때 사용자에게 보여지는 리뷰 화면(서브화면)
	public void displayKeyword(ArrayList<Review> list) {
		System.out.println("\n조회된 데이터를 다음과 같습니다.");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	
}
