package com._12atguigu.java.jikexueyuan.bridgems.bridge;

import com._12atguigu.java.jikexueyuan.bridgems.control.Control;

public abstract class TvControlabs {

	  Control mControl=null;
	public TvControlabs(Control mControl)
	{
		this.mControl=mControl;
	}
	
	public abstract void Onoff();
	public abstract void nextChannel();
	public abstract void preChannel();
	
	
}
