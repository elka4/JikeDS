package _8_WaiGuan_facademode;

import _8_WaiGuan_facademode.hometheater.HomeTheaterFacade;


public class MainTest {
	public static void main(String[] args) {
		HomeTheaterFacade mHomeTheaterFacade=new HomeTheaterFacade();
		
		mHomeTheaterFacade.ready();
		mHomeTheaterFacade.play();
	}
}
