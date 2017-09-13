package _6_MingLing_commandmode;

import _6_MingLing_commandmode.device.Light;
import _6_MingLing_commandmode.device.Stereo;

public class ControlTest {
	public static void main(String[] args) {
		Control ctl;
		Light light = new Light("Bedroom");
		Stereo stereo = new Stereo();
		ctl = new TraditionControl(light, stereo);

		ctl.onButton(0);
		ctl.offButton(0);
		ctl.onButton(1);
		ctl.onButton(2);
		ctl.offButton(2);

		ctl.offButton(1);
	}
}
