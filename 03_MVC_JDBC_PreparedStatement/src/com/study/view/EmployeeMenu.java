package com.study.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.study.controller.EmployeeController;
import com.study.model.vo.Department;
import com.study.model.vo.Employee;
import com.study.model.vo.Job;

public class EmployeeMenu {
	
	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();

	public void EmployeeMenu() {
		
		// 사용자가 보게될 화면 (메인화면)
		while(true) {
			
		System.out.println("\n=== 회사 관리 프로그램 ===");
		System.out.println("1. 전체 부서 조회하기(부서코드, 부서명만 조회)");
		System.out.println("2. 전체 직급 조회하기");
		System.out.println("3. 새로운 부서 추가하기");
		System.out.println("4. 새로운 직급 추가하기");
		System.out.println("5. 전체 사원 조회하기");
		System.out.println("6. 특정 사원 찾아서 부서변경하기");
		System.out.println("7. 특정 사원 찾아서 직급변경하기");
		System.out.println("8. 특정 사원 퇴사시키기");
		System.out.println("0. 프로그램 종료");
		System.out.print(">> 메뉴 선택 : ");
		int menu = sc.nextInt();
		sc.nextLine();
		
		 
		switch(menu){
			case 1 : ec.selectDept(); break;
			case 2 : ec.selectJob(); break;
			case 3 : insertDept(); break;
			case 4 : insertJob(); break;
			case 5 : ec.selectEmployee(); break;
			case 6 : updateDept(); break;
			case 7 : updateJob(); break;
			case 8 : updateEmpEntDate(); break;
			case 0 : System.out.println("\n프로그램을 종료합니다. 이용해주셔서 감사합니다."); return;
			default : System.out.println("메뉴를 잘못 입력하셨습니다. 다시 입력해주세요.");
			}

		}

	}
	
	// ---------------- 서비스 요청 화면 --------------------------

	// 3. 새로운 부서를 추가하기 위해 보여지는 화면
	public void insertDept() {
		System.out.print("\n부서 코드 : ");
		String deptId = sc.nextLine();
		System.out.print("부서명 : ");
		String deptTitle = sc.nextLine();
		System.out.print("지역코드 : ");
		String locationId = sc.nextLine();
		
		ec.insertDept(deptId, deptTitle, locationId);
	}
	
	// 4. 새로운 직급을 추가하기 위해 보여지는 화면
	public void insertJob() {
		System.out.print("\n직급 코드 : ");
		String jobCode = sc.nextLine();
		System.out.print("직급명 : ");
		String jobTitle = sc.nextLine();
		
		ec.insertJob(jobCode, jobTitle);
		
	}
	
	// 특정 사원을 조회하기위해 보여지는 화면
	public String selectEmpId() {
		
		System.out.print("\n사번 : ");
		return sc.nextLine();
		
	}
	
	// 특정 사원에 대한 부서 정보를 변경하기위해 보여지는 화면
	public void updateDept() {

		String empId = selectEmpId(); // 특정 사원의 사번 조회

		System.out.print("변경할 부서 코드 : ");
		String deptCode = sc.nextLine();

		ec.updateDept(empId, deptCode);

	}
	
	// 특정 사원에 대한 직급 정보를 변경하기위해 보여지는 화면
	public void updateJob() {
		
		String userId = selectEmpId();
		
		System.out.print("변경할 직급 코드 : ");
		String jobCode = sc.nextLine();
		
		ec.updateJob(userId, jobCode);
		
	}
	
	// 특정 사원을 찾아서 퇴사시키기 위해 보여지는 화면
	public void updateEmpEntDate() {

		String userId = selectEmpId();

		System.out.print("퇴사일(YY/MM/DD) : ");
		String entDate = sc.nextLine();
		System.out.print("퇴사여부(Y/N) : ");
		String entYn = sc.nextLine().toUpperCase();

		ec.updateEmpEntDate(userId, entDate, entYn);

	}
	
	// ---------------- 서비스 전달 화면 --------------------------

	// 조회 결과가 없는 경우 보여지는 화면
	public void displayNoData(String message) {
		System.out.println(message);
	}
	
	// 1. 조회 결과가 여러행일 경우 보여지는 화면(부서 조회)
	public void displaySelectDept(ArrayList<Department> deptList) {
		System.out.println("\n조회된 부서의 결과는 다음과 같습니다.\n");
		for(Department d : deptList) {
			System.out.println(d.information());
		}
	}
	
	// 2. 조회 결과가 여러행일 경우 보여지는 화면(직급 조회)
	public void displaySelectJob(ArrayList<Job> jobList) {
		System.out.println("\n조회 직급의 결과는 다음과 같습니다.\n");
		for (Job j : jobList) {
			System.out.println(j);
		}
	}
	
	// 5. 전체 사원 조회할 경우 보여지는 화면(사원 조회)
	public void displaySelectEmployee(ArrayList<Employee> list) {
		System.out.println("\n조회된 사원의 결과는 다음과 같습니다.\n");
		for(Employee e : list) {
			System.out.println(e);
		}
	}
	
	// 추가하고자하는 정보가 성공적으로 추가된 경우 보여지는 화면
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 성공 : " + message);
	} 
	
	// 추가하고자하는 정보가 추가되지 않은 경우 보여지는 화면
	public void displayFail(String message) {
		System.out.println("서비스 요청 실페 : " + message);
	}
	
}
