package _20_ZhongJieZhe_mediator;

public class Curtains extends Colleague {

	public Curtains(Mediator _20_ZhongJieZhe_mediator, String name) {
		super(_20_ZhongJieZhe_mediator, name);
		// TODO Auto-generated constructor stub
		_20_ZhongJieZhe_mediator.Register(name, this);
	}

	@Override
	public void SendMessage(int stateChange) {
		// TODO Auto-generated method stub
		this.GetMediator().GetMessage(stateChange, this.name);
	}

	public void UpCurtains() {
		System.out.println("I am holding Up Curtains!");
	}

}
