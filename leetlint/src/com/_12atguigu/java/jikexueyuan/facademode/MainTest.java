package com._12atguigu.java.jikexueyuan.facademode;

import com._12atguigu.java.jikexueyuan.facademode.hometheater.HomeTheaterFacade;


public class MainTest {
	public static void main(String[] args) {
		HomeTheaterFacade mHomeTheaterFacade=new HomeTheaterFacade();
		
		mHomeTheaterFacade.ready();
		mHomeTheaterFacade.play();
	}
}
