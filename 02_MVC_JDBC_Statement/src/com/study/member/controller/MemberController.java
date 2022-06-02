package com.study.member.controller;

import java.util.ArrayList;

import com.study.member.model.dao.MemberDao;
import com.study.member.model.vo.Member;
import com.study.member.view.MemberView;

public class MemberController {
	
	// Controller : View를 통해서 사용자가 요청한 기능에 대해 처리하는 담당
	//				해당 메소드로 전달된 데이터 [가공처리한 후] Dao메소드 호출 전달
	//				Dao로부터 반환받은 결과에 따라 성공/실패, 조회결과유무 판단해서
	//				사용자가 보게되는 응답화면 결정 (View 메소드 호출)
	
	
	/**
	 * 1. 회원 가입(추가) 요청 controller
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String phone) {
		
		// ----------------- 사용자에게 값 전달받기 --------------------
		
		// insert문 생성 => int 자료형으로 값 전달받기 => 트랜잭션 실행
	
		// 전달받은 값들을 dao에 전달하기 위해서 어딘가에 주섬주섬 담기 => Member 객체
		// userNo, enrollDate는 기본값으로 나가기 때문에 안담아도됨 => Member에 새로운 매개변수생성자 생성
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), phone);
		
		
		// ------------------ 성공 여부 전달하기 -----------------------
		
		// MemberDao클래스 호출
		// MemberDao클래스에 Member 객체에 담긴 값을 전달하면서 insertMember의 자료형 반환
		int result = new MemberDao().insertMember(m);
		
		// MemberView클래스 호출
		// MemberDao에서 return받은 값에 따른 메세지 전달
		if(result > 0) {
			new MemberView().displaySuccess("회원 가입에 성공했습니다.");
		} else {
			new MemberView().displayFail("회원 가입에 실패했습니다.");
		}
		
	}
	
	/**
	 * 2. 전체 회원 조회 요청 controller
	 */
	public void selectMemberList() {
		
		// MemberDao 클래스에서 전달받은 ArrayList<Member>의 조회값 전달하기
		ArrayList<Member> list = new MemberDao().selectMemberList();
		
		if(list.isEmpty()) {
			new MemberView().selectNoData("조회 결과가 없습니다.");
		} else {
			new MemberView().selectList(list);
		}
	}

}
