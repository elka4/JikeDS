package _20_ZhongJieZhe_mediator;

public class TV extends Colleague {

	public TV(Mediator _20_ZhongJieZhe_mediator, String name) {
		super(_20_ZhongJieZhe_mediator, name);
		// TODO Auto-generated constructor stub
		_20_ZhongJieZhe_mediator.Register(name, this);
	}

	@Override
	public void SendMessage(int stateChange) {
		// TODO Auto-generated method stub
		this.GetMediator().GetMessage(stateChange, this.name);
	}

	public void StartTv() {
		// TODO Auto-generated method stub
		System.out.println("It's time to StartTv!");
	}

	public void StopTv() {
		// TODO Auto-generated method stub
		System.out.println("StopTv!");
	}
}
