package commands;

import java.util.concurrent.Callable;

import utils.SimulatorConnector;

public class DisconnectCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		// TODO Auto-generated method stub
		SimulatorConnector.sendCommand("bye");
	}

}
