package com._12atguigu.java.hexter.stimulateduck.duck;

import com._12atguigu.java.hexter.stimulateduck.flybehavior.GoodFlyBehavior;
import com._12atguigu.java.hexter.stimulateduck.quackbehavior.GaGaQuackBehavior;

public class GreenHeadDuck extends Duck {

	public GreenHeadDuck() {
		mFlyBehavior = new GoodFlyBehavior();
		mQuackBehavior = new GaGaQuackBehavior();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("**GreenHead**");
	}
	
	//and override the inherited fly method here
	public void fly (){
		
	}

}