package com._12atguigu.java.jikexueyuan.agentmode.candymachine;

import java.io.Serializable;

public interface State extends Serializable{
	public void insertCoin();
	public void returnCoin();
	public void turnCrank();
	public void dispense();
	public void printstate();
	public String getstatename();
}