package com.br.model.vo;

// 영화리뷰
public class Review {

	private String movieNo;
	private String movieTitle;
	private String content;
	private int rate;
	
	public Review() {}

	public Review(String movieNo, String movieTitle, String content, int rate) {
		super();
		this.movieNo = movieNo;
		this.movieTitle = movieTitle;
		this.content = content;
		this.rate = rate;
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
		return "Review [movieNo=" + movieNo + ", movieTitle=" + movieTitle + ", content=" + content + ", rate=" + rate
				+ "]";
	}
	
	
}
