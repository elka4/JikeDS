package com._12atguigu.java.jikexueyuan.mediator;

public interface Mediator {
	public abstract void Register(String colleagueName, Colleague colleague);

	public abstract void GetMessage(int stateChange, String colleagueName);

	public abstract void SendMessage();
}
