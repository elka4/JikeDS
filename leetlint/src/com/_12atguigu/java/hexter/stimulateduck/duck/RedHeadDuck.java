package com._12atguigu.java.hexter.stimulateduck.duck;

import com._12atguigu.java.hexter.stimulateduck.flybehavior.BadFlyBehavior;

import com._12atguigu.java.hexter.stimulateduck.quackbehavior.GeGeQuackBehavior;

public class RedHeadDuck extends Duck {

	public RedHeadDuck() {
		mFlyBehavior = new BadFlyBehavior();
		mQuackBehavior = new GeGeQuackBehavior();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("**RedHead**");
	}

}
