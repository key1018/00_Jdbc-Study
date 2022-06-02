package com.study.model.vo;

import java.sql.Date;

// 영화정보
public class Movie {
	
	private String movieNo;
	private String movieTitle;
	private String movieAge;
	private Date openDay;
	private int rate;
	private int audience;
	
	public Movie() {}

	public Movie(String movieNo, String movieTitle, String movieAge, Date openDay, int rate, int audience) {
		super();
		this.movieNo = movieNo;
		this.movieTitle = movieTitle;
		this.movieAge = movieAge;
		this.openDay = openDay;
		this.rate = rate;
		this.audience = audience;
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

	public String getMovieAge() {
		return movieAge;
	}

	public void setMovieAge(String movieAge) {
		this.movieAge = movieAge;
	}

	public Date getOpenDay() {
		return openDay;
	}

	public void setOpenDay(Date openDay) {
		this.openDay = openDay;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getAudience() {
		return audience;
	}

	public void setAudience(int audience) {
		this.audience = audience;
	}

	@Override
	public String toString() {
		return "Movie [movieNo=" + movieNo + ", movieTitle=" + movieTitle + ", movieAge=" + movieAge + ", openDay="
				+ openDay + ", rate=" + rate + ", audience=" + audience + "]";
	}

}
