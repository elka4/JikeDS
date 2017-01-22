package com._12atguigu.java.jikexueyuan.commandmode;

public interface Control {

	public void onButton(int slot);

	public void offButton(int slot);
	
	public void undoButton();
}
