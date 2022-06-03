package com.study.model.vo;

public class Department {
	
	private String deptId;
	private String deptTitle;
	private String locationNo;
	
	public Department() {
		
	}

	public Department(String deptId, String deptTitle, String locationNo) {
		super();
		this.deptId = deptId;
		this.deptTitle = deptTitle;
		this.locationNo = locationNo;
	}

	public Department(String deptId, String deptTitle) {
		super();
		this.deptId = deptId;
		this.deptTitle = deptTitle;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptTitle() {
		return deptTitle;
	}

	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}

	public String getLocationNo() {
		return locationNo;
	}

	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptTitle=" + deptTitle + ", locationNo=" + locationNo + "]";
	}
	
	public String information() {
		return "Department [deptId=" + deptId + ", deptTitle=" + deptTitle + "]";
	}

}
