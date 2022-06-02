package com.study.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.study.member.model.vo.Member;

public class MemberDao {
	
	// DAO (Data Access Object)
	// DB에 직접 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과받기(즉, jdbc과정을 여기서)
	// 결과를 MemberController로 다시 반환
	
	
	/**
	 * 1. 회원 정보 추가하는 jdbc
	 * @param m : 사용자에게 입력받은 회원 정보가 들어있는 Member 객체
	 * @return : 결과값을 담은 result변수 (성공 : 1 | 실패 : 0)
	 */
	public int insertMember(Member m) { // MemberController에서 Member에 들어가있는 데이터값 전달받음
		
		// insert문 생성 => int 자료형으로 값 전달받기 => 트랜잭션 실행
		
		// 1. 결과값을 담을 int 변수 선언
		int result = 0; 
		
		// 2. jdbc에 필요한 객체 미리 생성
		Connection conn = null;
		Statement stmt = null;
		
		// 3. 실행할 sql문 생성
		String sql = "INSERT INTO TB_회원 VALUES (SEQ_회원.NEXTVAL, '" + m.getUserId() + "', "
												+ "'" + m.getUserPwd() + "', " + "'" + m.getUserName() + "', " 
												+ "'" + m.getGender() + "', " + m.getAge() + ", "
												+ "'" + m.getPhone() + "', SYSDATE)";
		
		System.out.println(sql);
		
		try {
			// 4. jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 5. Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KEY", "KEY");
			// 6. Statement 객체 생성
			stmt = conn.createStatement();
			// 7. result 변수에 결과값 담기
			result = stmt.executeUpdate(sql);
			
			// 8. 트랜잭션 실행
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 9. 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 10. 결과값 return하면 반환
		return result; // 회원 추가 성공 : 1 | 실패 : 0

	}
	
	/** 
	 * 2. 전체 회원 조회하는 jdbc
	 * @return : 회원 정보들이 담겨있는 ArrayList(텅 비어있는 경우 | 데이터가 들어있는 경우)
	 */
	public ArrayList<Member> selectMemberList() {
		
		// SELECT문 => ResultSet 객체 생성 => ArrayList<Member> 에 결과값 담기
		
		// 1. 결과값을 담을 ArrayList객체 생성
		ArrayList<Member> list = new ArrayList<>(); // 텅 빈 상태
		
		// 2. jdbc에 필요한 객체 미리 생성
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 3. 실행할 sql문 선언
		String sql = "SELECT * FROM TB_회원";
		
		try {
			// 4. driver 및 객체 선언 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KEY", "KEY");
			stmt = conn.createStatement();
			// 5. rset에 결과값 담기
			rset = stmt.executeQuery(sql);
			// 6. while(rset.next())문을 활용하여 값 ArrayList에 담기
			while (rset.next()) {

				Member m = new Member(rset.getInt("user_no"), rset.getString("user_id"), rset.getString("user_pwd"),
						rset.getString("user_name"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("phone"), rset.getDate("enroll_date"));

				list.add(m);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7. 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 8. 결과값 반환
		return list; // 데이터 없으면 : 텅 빈 리스트 | 데이터 있으면 : list출력

	}

}
