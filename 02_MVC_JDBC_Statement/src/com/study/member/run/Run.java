package com.study.member.run;

import com.study.member.view.MemberView;

public class Run {

	public static void main(String[] args) {
		
		/*
		 * M (Model) : 데이터 처리 담당 (데이터들을 담기위한 클래스(vo), DB와 직접적으로 접촉해서 데이터를 주고받는 클래스(dao), ...)
		 * V (View)  : 사용자게에 보여주는 화면 담당 (사용자에게 시각적인 요소 제공, 출력 및 입력)
		 * C (Controller) : 사용자의 요청을 처리해주는 담당 (사용자의 요청을 처리한 후에 그에 해당하는 응답화면을 지정하여 데이터 전송)
		 */
		
		MemberView  m = new MemberView();
		m.MemberMenu();

	}

}
