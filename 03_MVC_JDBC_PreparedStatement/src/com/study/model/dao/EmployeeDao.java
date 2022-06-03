package com.study.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.study.model.vo.Department;
import com.study.model.vo.Employee;
import com.study.model.vo.Job;

public class EmployeeDao {

	/**
	 * 1. 전체 부서 조회 요청처리하는 jdbc
	 * @return : 부서 조회결과가 담겨있는 ArrayList<Department>
	 */
	public ArrayList<Department> selectDept(){
		
		// select문 => ResultSet => ArrayList<Dept>에 담음
		
		// 결과값을 담을 ArrayList<Dept> 생성
		ArrayList<Department> deptList = new ArrayList<>();
		
		// 객체 생성
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rset = null;
		
		// 실행할 sql문 생성
		String sql = "SELECT DEPT_ID, DEPT_TITLE FROM DEPARTMENT"; // 완성된 sql문
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BR","BR"); // 애초에 '완성된' sql문을 담으면서 생성	=> 곧바로 실행
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery(); // 완성된 sql문 전달
			
			while (rset.next()) {

				deptList.add(new Department(rset.getString("DEPT_ID"), rset.getString("DEPT_TITLE")));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return deptList; // 조회 결과가 없는 경우 : 텅 빈 리스트 | 조회 결과가 있는 경우 : 뭐라도 담겨있는 리스트

	}
	
	/**
	 * 2. 전체 직급 조회 요청처리하는 jdbc
	 * @return 직급 조회 결과가 담겨있는 ArrayList<Job> 객체
	 */
	public ArrayList<Job> selectJob(){
		
		// select문 => ResultSet => ArrayList<Job>
		
		ArrayList<Job> jobList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문
		String sql = "SELECT * FROM JOB";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BR", "BR");
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			// rset에 있는 조회된 데이터 ArrayList<Job>에 담기
			while(rset.next()) {
				jobList.add(new Job(rset.getString("JOB_CODE"), rset.getString("JOB_NAME")));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return jobList; // 조회 결과가 없는 경우 : 텅 빈 리스트 | 조회 결과가 있는 경우 : 뭐라도 담겨있는 리스트
		
	}
	
	/**
	 * 3. 부서 정보 추가 요청하는 jdbc
	 * @param deptId : 추가하고자하는 부서코드
	 * @param deptTitle : 추가하고자하는 부서명
	 * @param locationId : 추가하고자하는 부서지역코드
	 * @return : 부서정보가 추가됐는지 확인하는 정수값 (추가 성공 : 1 | 실패 : 0)
	 */
	public int insertDept(String deptId, String deptTitle, String locationId) {
		
		// insert(DML문) => int => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO DEPARTMENT VALUES(?, ?, ?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BR", "BR");
			pstmt = conn.prepareStatement(sql); // 미완성된 sql문
			
			// 완성된 sql문으로 만들기
			pstmt.setString(1, deptId);
			pstmt.setString(2, deptTitle);
			pstmt.setString(3, locationId);
			
			// 완성된 sql문을 result에 담기
			result = pstmt.executeUpdate();
			
			// 트랜잭션 실행
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 결과값 return
		return result; // 삽입 성공 : 1 | 실패 : 0

	}
	
	/**
	 * 4. 직급 정보 추가 요청처리하는 jdbc
	 * @param jobCode : 추가하고자하는 직급코드
	 * @param jobTitle : 추가하고자하는 직급명 
	 * @return : 직급정보가 추가됐는지 확인하는 정수값 (추가 성공 : 1 | 실패 : 0)
	 */
	public int insertJob(String jobCode, String jobName) {
		
		// insert(DML문) => int => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO JOB VALUES(?, ?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BR", "BR");
			pstmt = conn.prepareStatement(sql); // 미완성된 sql문
			
			// 완성된 sql문으로 만들기
			pstmt.setString(1, jobCode);
			pstmt.setString(2, jobName);
			
			// 완성된 sql문을 result에 담기
			result = pstmt.executeUpdate();
			
			// 트랜잭션 실행
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result; // 삽입 성공 : 1 | 실패 : 0

	}
	
	/**
	 * 5. 전체 회원 정보 조회를 요청을 처리하는 jdbc
	 * @return 전체 회원 정보가 담긴 ArrayList<Employee> 객체
	 */
	public ArrayList<Employee> selectEmployee(){
		
		// select문 => ResultSet => ArrayList<Employee>
		
		ArrayList<Employee> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM EMPLOYEE";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BR", "BR");
			pstmt = conn.prepareStatement(sql); // 완성된 sql문 
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Employee(rset.getString("EMP_ID"), 
									rset.getString("EMP_NAME"), 
									rset.getString("EMP_NO"),
									rset.getString("EMAIL"), 
									rset.getString("PHONE"), 
									rset.getString("DEPT_CODE"),
									rset.getString("JOB_CODE"),
									rset.getInt("SALARY"), 
									rset.getInt("BONUS"),
									rset.getString("MANAGER_ID"),
									rset.getDate("HIRE_DATE"), 
									rset.getDate("ENT_DATE"),
									rset.getString("ENT_YN")));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}
	
	/**
	 * 6. 특정 사원을 찾아서 부서 코드를 변경하는 메소드
	 * @param empId : 변경을 원하는 사원의 사번
	 * @param deptCode : 변경할 부서코드
	 * @return : 변경 결과가 담긴 int 값 (변경 성공 : 1 | 변경 실패 : 0)
	 */
	public int updateDept(String empId, String deptCode) {
		
		// insert(DML문) => int => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE EMPLOYEE SET DEPT_CODE = ? WHERE EMP_ID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BR", "BR");
			pstmt = conn.prepareStatement(sql); // 미완성된 sql문

			// 완성된 sql문으로 만들기
			pstmt.setString(1, deptCode);
			pstmt.setString(2, empId);

			// 완성된 sql문을 result에 담기
			result = pstmt.executeUpdate();

			// 트랜잭션 실행
			if (result > 0) {
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result; // 변경 성공 : 1 | 실패 : 0

	}
	
	/**
	 * 7. 특정 사원을 찾아서 직급 코드를 변경하는 jdbc
	 * @param empId : 변경을 원하는 사원의 사번
	 * @param jobCode : 변경할 직급코드
	 * @return : 변경 결과가 담긴 int 값 (변경 성공 : 1 | 변경 실패 : 0)
	 */
	public int updateJob(String empId, String jobCode) {
		
		// insert(DML문) => int => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE EMPLOYEE SET JOB_CODE = ? WHERE EMP_ID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BR", "BR");
			pstmt = conn.prepareStatement(sql); // 미완성된 sql문

			// 완성된 sql문으로 만들기
			pstmt.setString(1, jobCode);
			pstmt.setString(2, empId);

			// 완성된 sql문을 result에 담기
			result = pstmt.executeUpdate();

			// 트랜잭션 실행
			if (result > 0) {
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result; // 변경 성공 : 1 | 실패 : 0

	}
	
	/**
	 * 8. 특정 사원을 찾아서 퇴사 정보를 변경하는 jdbc
	 * @param empId : 변경을 원하는 사원의 사번
	 * @param entDate : 변경할 퇴사날짜
	 * @param entYn : 변경할 퇴사여부
	 * @return : 퇴사여부에 대한 변경 결과가 담긴 int값 (변경 성공 : 1 | 실패 : 0)
	 */
	public int updateEmpEntDate(String empId, String entDate, String entYn) {
		
		// insert(DML문) => int => 트랜잭션 실행
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE EMPLOYEE SET ENT_DATE = ?, ENT_YN = ? WHERE EMP_ID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BR", "BR");
			pstmt = conn.prepareStatement(sql); // 미완성된 sql문

			// 완성된 sql문으로 만들기
			pstmt.setString(1, entDate);
			pstmt.setString(2, entYn);
			pstmt.setString(3, empId);

			// 완성된 sql문을 result에 담기
			result = pstmt.executeUpdate();

			// 트랜잭션 실행
			if (result > 0) {
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과값 return
		return result; // 변경 성공 : 1 | 실패 : 0

	}

}
