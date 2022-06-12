package com.study.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.study.controller.MovieController;
import com.study.model.vo.Member;
import com.study.model.vo.Movie;
import com.study.model.vo.Reserve;
import com.study.model.vo.Review;

public class MovieMenu {
	
	Scanner sc = new Scanner(System.in);
	
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
			case 6 : selectKeyword(); break;
			case 7 : updateMember(); break;
			case 8 : deleteMember(); break;
			case 0 : System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다."); return;
			default : System.out.println("메뉴를 다시 선택해주세요.");
			} // switch문 끝

		}// while문 끝

	} // MemberMenu 끝
	
	//------------------------서비스 요청 화면---------------------
	
	// 1. 회원 추가시 사용자에게 보여지는 화면(서브화면)
	public void insertMember() {
		
		System.out.println("\n=== 회원 가입 ===");
		
		System.out.print("\n아이디 : ");
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
	
	// 2. 영화 정보 추가시 사용자에게 보여지는 화면(서브화면)
	
	public void insertMovie() {
		
		System.out.println("\n=== 영화 정보 추가 ===");
		
		System.out.print("\n영화제목 : ");
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
		
		System.out.print("\n영화번호 : ");
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
		
		System.out.print("\n아이디 : ");
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
	
	// 5. 전체 영화/리뷰 정보 조회 요청시 사용자에게 보여지는 화면
	public void selectAll() {
		
		System.out.println("\n1. 영화 조회");
		System.out.println("2. 리뷰 조회");
		System.out.print(">> 메뉴 선택 : ");
		int num = sc.nextInt();
		
		if(num == 1) {
			new MovieController().selectMovie();
		} else if (num == 2) {
			new MovieController().selectReview();
		}
		
	}
	
	// 6. 영화 이름(키워드)에 따른 리뷰 검색 요청시 사용자에게 보여지는 화면
	public void selectKeyword() {
		
		System.out.print("\n영화 제목(키워드 가능) 입력 : ");
		String keyword = sc.nextLine();
		
		mc.selectKeyword(keyword);
		
	}
	
	// 7. 회원 정보 수정 요청시 사용자에게 보여지는 화면
	public void updateMember() {
		
		System.out.print("\n변경하고자하는 아이디 : ");
		String userId = sc.nextLine();
		
		System.out.println("\n=== 정보 변경 ===");
		
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		
		mc.updateMember(userPwd, phone, userId);
		
	}
	
	// 8. 회원 정보 삭제 요청시 사용자에게 보여지는 화면
	public void deleteMember() {
		
		System.out.println("\n=== 회원 탈퇴 ===");
		
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		
		mc.deleteMember(userId);
		
	}
	
	
	
	//------------------------서비스 처리 화면---------------------
	
	// 1. 회원가입 성공시 사용자에게 보여지는 화면
	public void successInsertM(Member m) {
		
		System.out.println("\n회원가입에 성공했습니다.");
		System.out.println(m);
	}
	
	// 2. 요청 실패시 사용자에게 보여지는 화면
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	// 3. 요청 성공시 사용자에게 보여지는 화면
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	// 4. 예매 성공시 사용자에게 보여지는 화면
	public void successInsertR(Reserve r) {
		System.out.println("\n예매정보가 등록되었습니다.");
		System.out.println(r);
	}
	
	// 5. 리뷰 정보 등록 성공시 사용자에게 보여지는 화면
	public void successInsertRe(Review re) {
		
		System.out.println("\n리뷰 정보가 등록되었습니다.");
		System.out.println(re);
	}
	
	// 6. 조회 결과가 없을시 사용자게에 보여지는 화면
	public void displayNoData(String message) {
		
		System.out.println(message);
	
	}
	
	// 7. 전체 영화 조회 결과가 있을시 사용자에게 보여지는 화면
	public void displayMovie(ArrayList<Movie> movie) {
		
		System.out.println("\n전체 영화 조회 결과 내역입니다.");
	
		for(Movie m : movie) {
			System.out.println(m);
		}
	}
	
	
	// 7. 전체 리뷰 조회 결과가 있을시 사용자에게 보여지는 화면
	public void displayReview(ArrayList<Review> review) {
		
		System.out.println("\n전체 영화 조회 결과 내역입니다.");
	
		for(Review r : review) {
			System.out.println(r);
		}
	}
	
	public void displayKeyword(String message, ArrayList<Movie> list) {
		
		System.out.println(message);
		
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}

}
