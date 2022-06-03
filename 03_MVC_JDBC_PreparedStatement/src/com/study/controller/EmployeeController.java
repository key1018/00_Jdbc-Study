package com.study.controller;

import java.util.ArrayList;

import com.study.model.dao.EmployeeDao;
import com.study.model.vo.Department;
import com.study.model.vo.Employee;
import com.study.model.vo.Job;
import com.study.view.EmployeeMenu;

public class EmployeeController {

	/**
	 * 1. 전체 부서 조회 요청처리하는 메소드 (부서코드, 부서명만 조회)
	 */
	public void selectDept() {

		ArrayList<Department> deptList = new EmployeeDao().selectDept();

		if (deptList.isEmpty()) {
			new EmployeeMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new EmployeeMenu().displaySelectDept(deptList);
		}

	}
	
	/**
	 * 2. 전체 직급 조회 요청처리하는 메소드
	 */
	public void selectJob() {
		
		ArrayList<Job> jobList = new EmployeeDao().selectJob();
		
		if(jobList.isEmpty()) {
			new EmployeeMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new EmployeeMenu().displaySelectJob(jobList);
		}
		
	}
	
	/**
	 * 3. 부서 정보 추가 요청처리하는 메소드
	 * @param deptId : 추가하고자하는 부서코드
	 * @param deptTitle : 추가하고자하는 부서명
	 * @param locationId : 추가하고자하는 부서지역코드
	 */
	public void insertDept(String deptId, String deptTitle, String locationId) {
		
		int result = new EmployeeDao().insertDept(deptId, deptTitle, locationId);
		
		if(result > 0) {
			new EmployeeMenu().displaySuccess("성공적으로 부서정보가 추가되었습니다.");
		} else {
			new EmployeeMenu().displayFail("부서정보를 추가하는데 실패했습니다.");
		}

	}
	
	/**
	 * 4. 직급 정보 추가 요청처리하는 메소드
	 * @param jobCode : 추가하고자하는 직급코드
	 * @param jobTitle : 추가하고자하는 직급명 
	 */
	public void insertJob(String jobCode, String jobTitle) {
		
		int result = new EmployeeDao().insertJob(jobCode, jobTitle);
		
		if(result > 0) {
			new EmployeeMenu().displaySuccess("성공적으로 직급정보가 추가되었습니다.");
		} else {
			new EmployeeMenu().displayFail("직급정보를 추가하는데 실패했습니다.");
		}
		
	}
	
	/**
	 * 5. 전체 회원 정보 조회를 요청하는 메소드
	 */
	public void selectEmployee() {
		
		ArrayList<Employee> list = new EmployeeDao().selectEmployee();
		
		if(list.isEmpty()) {
			new EmployeeMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new EmployeeMenu().displaySelectEmployee(list);
		}
	}
	
	/**
	 * 6. 특정 사원을 찾아서 부서 코드를 변경하는 메소드
	 * @param empId : 변경을 원하는 사원의 사번
	 * @param deptCode : 변경할 부서코드
	 */
	public void updateDept(String empId, String deptCode) {
		int result = new EmployeeDao().updateDept(empId, deptCode);
		
		if(result > 0) {
			new EmployeeMenu().displaySuccess("성공적으로 부서정보가 변경되었습니다.");
		} else {
			new EmployeeMenu().displayFail(empId + "에 대한 조회 결과를 찾지못했습니다.");
		}
	}
	
	/**
	 * 7. 특정 사원을 찾아서 직급 코드를 변경하는 메소드
	 * @param empId : 변경을 원하는 사원의 사번
	 * @param jobCode : 변경할 직급코드 
	 */
	public void updateJob(String empId, String jobCode) {

		int result = new EmployeeDao().updateJob(empId, jobCode);

		if (result > 0) {
			new EmployeeMenu().displaySuccess("성공적으로 직급정보가 변경되었습니다.");
		} else {
			new EmployeeMenu().displayFail(empId + "에 대한 조회 결과를 찾지못했습니다.");
		}

	}
	
	/**
	 * 8. 특정 사원을 찾아서 퇴사 정보를 변경하는 메소드
	 * @param empId : 변경을 원하는 사원의 사번
	 * @param entDate : 변경할 퇴사날짜
	 * @param entYn : 변경할 퇴사여부
	 */
	public void updateEmpEntDate(String empId, String entDate, String entYn) {

		int result = new EmployeeDao().updateEmpEntDate(empId, entDate, entYn);

		if (result > 0) {
			new EmployeeMenu().displaySuccess("성공적으로 퇴사정보가 변경되었습니다.");
		} else {
			new EmployeeMenu().displayFail(empId + "에 대한 조회 결과를 찾지못했습니다.");
		}

	}

}
