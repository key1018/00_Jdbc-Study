package com.study.member.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.study.member.controller.MemberController;
import com.study.member.model.vo.Member;

public class MemberView {

	// View : 사용자가 보게될 화면으로 입력(Scanner) 및 출력(print) 제공
	
	// Scanner 객체 생성
	private Scanner sc = new Scanner(System.in);
	// Member 객체 생성
	Member m = new Member();	
	
	// MemberController 클래스 호출
	MemberController mc = new MemberController();
	
	// 사용자가 보게될 회원 정보 관리 화면(메인화면)
	public void MemberMenu() {
		
		while(true) { // 반복적으로 호출
			
			System.out.println("\n=== 회원 관리 프로그램 ===");
			System.out.println("1. 회원 추가");
			System.out.println("2. 전체 회원 조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름(키워드) 검색");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print(">> 메뉴를 선택하세요 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : insertMember(); break;
			case 2 : selectMember(); break;
			case 3 : break;
			case 4 : break;
			case 5 : break;
			case 6 : break;
			case 0 : System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다."); return;
			default : System.out.println("메뉴를 다시 선택해주세요.");
			} // switch문 끝

		}// while문 끝

	} // MemberMenu 끝
	
	//------------------------서비스 요청 화면---------------------
	
	// 1. 회원 추가하는 화면 (서브화면)
	public void insertMember() {
		
		System.out.println("\n=== 회원 추가 ===");
		
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		System.out.print("성별 : ");
		String gender = sc.nextLine().toUpperCase();
		System.out.print("나이 : ");
		String age = sc.nextLine(); // 데이터를 외부파일에 전달할 떄 문자열로 전달되므로 String으로 선언
		System.out.print("전화번호(-빼고 입력) : ");
		String phone = sc.nextLine();
		
		// 사용자에게 입력받은 값들을 MemberController 클래스에 전달하면서 호출
		
		mc.insertMember(userId, userPwd, userName, gender, age, phone);

	}
	
	// 2. 전체 회원 조회하는 화면 (서브화면)
	public void selectMember() {
		
		// MemberController클래스의 회원 조회하는 메소드 호출
		new MemberController().selectMemberList();
	}
	
	
	//----------------------- 서비스 응답 화면 ----------------------------

	// 서비스 요청 성공했을 때 회원에게 보여지는 서브화면
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 성공 : " + message);
	} 
	
	// 서비스 요청 실패했을 때 회원에게 보여지는 서브화면
	public void displayFail(String message) {
		System.out.println("서비스 요청 실패 : " + message); 
	}
	
	// 전체 회원 조회할 때 조회 결과가 없을 때 보여지는 서브화면
	public void selectNoData(String message) {
		System.out.println(message);
	} 
	
	// 전체 화면 조회할 때 조회 결과가 있을 때 보여지는 서브화면
	public void selectList(ArrayList<Member> list) {
		System.out.println("\n조회 결과는 다음과 같습니다.");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	
}
