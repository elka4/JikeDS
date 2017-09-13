package _20_ZhongJieZhe_mediator;

public class MainTest {

	public static void main(String[] args) {
		Mediator _20_ZhongJieZhe_mediator = new ConcreteMediator();
		Alarm mAlarm = new Alarm(_20_ZhongJieZhe_mediator, "mAlarm");
		CoffeeMachine mCoffeeMachine = new CoffeeMachine(_20_ZhongJieZhe_mediator,
				"mCoffeeMachine");
		Curtains mCurtains = new Curtains(_20_ZhongJieZhe_mediator, "mCurtains");
		TV mTV = new TV(_20_ZhongJieZhe_mediator, "mTV");
		mAlarm.SendAlarm(0);
		mCoffeeMachine.FinishCoffee();
		mAlarm.SendAlarm(1);
	}

}
