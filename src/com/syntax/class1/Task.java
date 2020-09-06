package com.syntax.class1;

import org.testng.annotations.Test;

public class Task {
	
	@Test()
	public void chromeDriver() {
		System.out.println("I am a Chrome Browser");
	}

	@Test
	public void firefoxDriver() {
		System.out.println("I am FireFox Driver");
	}

	@Test
	public void IE() {
		System.out.println("I am an Internet Exploere Browser");
	}

}
