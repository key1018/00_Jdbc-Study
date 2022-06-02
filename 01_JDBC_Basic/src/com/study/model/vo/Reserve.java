package com.study.model.vo;

// 영화예약
public class Reserve {
	
	private String movieNo;
	private String movieTitle;
	private int audience;
	private String confirmed; 
	
	public Reserve() {}

	public Reserve(String movieNo, String movieTitle, int audience, String confirmed) {
		super();
		this.movieNo = movieNo;
		this.movieTitle = movieTitle;
		this.audience = audience;
		this.confirmed = confirmed;
	}

	public String getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(String movieNo) {
		this.movieNo = movieNo;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
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
		return "Reserve [movieNo=" + movieNo + ", movieTitle=" + movieTitle + ", audience=" + audience + ", confirmed="
				+ confirmed + "]";
	}

}
