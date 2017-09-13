package _21_BeiWangZhe_memento;

import java.util.HashMap;

public class MementoCaretaker {
	private HashMap<String, MementoIF> _21_BeiWangZhe_mementomap;

	public MementoCaretaker() {
		_21_BeiWangZhe_mementomap = new HashMap<String, MementoIF>();
	}

	public MementoIF retrieveMemento(String name) {
		return _21_BeiWangZhe_mementomap.get(name);
	}

	/**
	 * 备忘录赋值方法
	 */
	public void saveMemento(String name, MementoIF _21_BeiWangZhe_memento) {
		this._21_BeiWangZhe_mementomap.put(name, _21_BeiWangZhe_memento);
	}
}
