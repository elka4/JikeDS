package _20_ZhongJieZhe_mediator;

public abstract class Colleague {
	private Mediator _20_ZhongJieZhe_mediator;
	public String name;

	public Colleague(Mediator _20_ZhongJieZhe_mediator, String name) {

		this._20_ZhongJieZhe_mediator = _20_ZhongJieZhe_mediator;
		this.name = name;

	}

	public Mediator GetMediator() {
		return this._20_ZhongJieZhe_mediator;
	}

	public abstract void SendMessage(int stateChange);
}
