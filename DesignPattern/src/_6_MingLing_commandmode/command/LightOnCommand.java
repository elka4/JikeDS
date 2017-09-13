package _6_MingLing_commandmode.command;

import _6_MingLing_commandmode.device.Light;

public class LightOnCommand implements Command {
	private Light light;
	
	public LightOnCommand(Light light)
	{
		this.light=light;
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		light.On();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		light.Off();
	}

}
