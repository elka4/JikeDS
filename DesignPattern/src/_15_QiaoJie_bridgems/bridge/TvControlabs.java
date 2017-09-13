package _15_QiaoJie_bridgems.bridge;

import _15_QiaoJie_bridgems.control.Control;

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
