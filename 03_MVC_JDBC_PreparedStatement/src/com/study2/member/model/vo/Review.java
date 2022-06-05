package com.study2.member.model.vo;

public class Review {
	
	private String userId;
	private String movieNo;
	private String movieTitle;
	private String content;
	private int rate;
	
	public Review() {
		
	}

	public Review(String userId, String movieNo, String movieTitle, String content, int rate) {
		super();
		this.userId = userId;
		this.movieNo = movieNo;
		this.movieTitle = movieTitle;
		this.content = content;
		this.rate = rate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Review [아이디 = " + userId + ", 영화번호 = " + movieNo + ", 영화제목 = " + movieTitle + ", 리뷰내용 = "
				+ content + ", 평점 = " + rate + "]";
	}

}
