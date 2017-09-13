package _7_ShiPeiQi_adaptermode.adapter;
import _7_ShiPeiQi_adaptermode.duck.Duck;
import _7_ShiPeiQi_adaptermode.turkey.WildTurkey;

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
