package _20_ZhongJieZhe_mediator;

public class Alarm extends Colleague {

	public Alarm(Mediator _20_ZhongJieZhe_mediator, String name) {
		super(_20_ZhongJieZhe_mediator, name);
		// TODO Auto-generated constructor stub
		_20_ZhongJieZhe_mediator.Register(name, this);
	}

	public void SendAlarm(int stateChange) {
		SendMessage(stateChange);
	}

	@Override
	public void SendMessage(int stateChange) {
		// TODO Auto-generated method stub
		this.GetMediator().GetMessage(stateChange, this.name);
	}

}
