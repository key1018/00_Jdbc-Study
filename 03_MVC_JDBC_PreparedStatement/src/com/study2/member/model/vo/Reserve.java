package com.study2.member.model.vo;

public class Reserve {
	
	private String movieNo;
	private int audience;
	private String confirmed;
	
	public Reserve() {
		
	}

	public Reserve(String movieNo, int audience, String confirmed) {
		super();
		this.movieNo = movieNo;
		this.audience = audience;
		this.confirmed = confirmed;
	}

	public String getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(String movieNo) {
		this.movieNo = movieNo;
	}

	public int getAudience() {
		return audience;
	}

	public void setAudience(int audience) {
		this.audience = audience;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public String toString() {
		return "Reserve [영화번호 = " + movieNo + ", 관객수 = " + audience + ", 예매상태 = "
				+ confirmed + "]";
	}

}
