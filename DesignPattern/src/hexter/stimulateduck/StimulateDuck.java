package hexter.stimulateduck;

import hexter.stimulateduck.duck.Duck;
import hexter.stimulateduck.duck.GreenHeadDuck;
import hexter.stimulateduck.duck.RedHeadDuck;
import hexter.stimulateduck.flybehavior.NoFlyBehavior;
import hexter.stimulateduck.quackbehavior.NoQuackBehavior;


public class StimulateDuck {

	public static void main(String[] args) {

		Duck mGreenHeadDuck = new GreenHeadDuck();
		Duck mRedHeadDuck = new RedHeadDuck();

		mGreenHeadDuck.display();
		mGreenHeadDuck.Fly();
		mGreenHeadDuck.Quack();
		mGreenHeadDuck.swim();

		mRedHeadDuck.display();
		mRedHeadDuck.Fly();
		mRedHeadDuck.Quack();
		mRedHeadDuck.swim();
		mRedHeadDuck.display();
		mRedHeadDuck.SetFlyBehavoir(new NoFlyBehavior());
		mRedHeadDuck.Fly();
		mRedHeadDuck.SetQuackBehavoir(new NoQuackBehavior());
		mRedHeadDuck.Quack();
	}

}
