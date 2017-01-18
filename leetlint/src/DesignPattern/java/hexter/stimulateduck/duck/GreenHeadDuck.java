package DesignPattern.java.hexter.stimulateduck.duck;

import com.java.hexter.stimulateduck.flybehavior.GoodFlyBehavior;
import com.java.hexter.stimulateduck.quackbehavior.GaGaQuackBehavior;

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