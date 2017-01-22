package com._12atguigu.java.jikexueyuan.adaptermode.adapter;

import com._12atguigu.java.jikexueyuan.adaptermode.duck.Duck;
import com._12atguigu.java.jikexueyuan.adaptermode.turkey.WildTurkey;

public class TurkeyAdapter2 extends WildTurkey implements Duck {

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		super.gobble();
	}
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		super.fly();
		super.fly();
		super.fly();
	}
}
