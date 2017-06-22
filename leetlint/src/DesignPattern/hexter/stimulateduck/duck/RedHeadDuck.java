package DesignPattern.hexter.stimulateduck.duck;

import DesignPattern.hexter.stimulateduck.flybehavior.BadFlyBehavior;
import DesignPattern.hexter.stimulateduck.quackbehavior.GeGeQuackBehavior;

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
