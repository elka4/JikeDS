package com._12atguigu.java.jikexueyuan.adaptermode;

import com._12atguigu.java.jikexueyuan.adaptermode.adapter.TurkeyAdapter2;
import com._12atguigu.java.jikexueyuan.adaptermode.duck.Duck;
import com._12atguigu.java.jikexueyuan.adaptermode.duck.GreenHeadDuck;
import com._12atguigu.java.jikexueyuan.adaptermode.turkey.WildTurkey;


public class MainTest {
	public static void main(String[] args) {
		GreenHeadDuck duck=new GreenHeadDuck();
		
		WildTurkey turkey=new WildTurkey();
		
		Duck duck2turkeyAdapter=new TurkeyAdapter2();
		turkey.gobble();
		turkey.fly();
		duck.quack();
		duck.fly();
		duck2turkeyAdapter.quack();
		duck2turkeyAdapter.fly();
		
	
	}
}
