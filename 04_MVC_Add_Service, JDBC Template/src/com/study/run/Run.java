package com.study.run;

import com.study.view.MovieMenu;

public class Run {

	public static void main(String[] args) {
		
		MovieMenu mm = new MovieMenu();
		
		
		// 순서
		// MovieMenu -> MovieController -> MovieService -> MovieDao
		mm.MovieMenu();

	}

}
