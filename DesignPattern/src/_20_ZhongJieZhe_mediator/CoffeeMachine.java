package _20_ZhongJieZhe_mediator;

public class CoffeeMachine extends Colleague {

	public CoffeeMachine(Mediator _20_ZhongJieZhe_mediator, String name) {
		super(_20_ZhongJieZhe_mediator, name);
		// TODO Auto-generated constructor stub
		_20_ZhongJieZhe_mediator.Register(name, this);
	}

	@Override
	public void SendMessage(int stateChange) {
		// TODO Auto-generated method stub
		this.GetMediator().GetMessage(stateChange, this.name);
	}

	public void StartCoffee() {
		System.out.println("It's time to startcoffee!");
	}

	public void FinishCoffee() {

		System.out.println("After 5 minutes!");
		System.out.println("Coffee is ok!");
		SendMessage(0);
	}
}
