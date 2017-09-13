package _1_CeLue_stimulateduck;

import _1_CeLue_stimulateduck.duck.Duck;
import _1_CeLue_stimulateduck.duck.GreenHeadDuck;
import _1_CeLue_stimulateduck.duck.RedHeadDuck;
import _1_CeLue_stimulateduck.flybehavior.NoFlyBehavior;
import _1_CeLue_stimulateduck.quackbehavior.NoQuackBehavior;


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
