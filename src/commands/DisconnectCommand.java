package commands;

import java.util.concurrent.Callable;

import test.MyInterpreter;
import utils.SimulatorConnector;

public class DisconnectCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		// TODO Auto-generated method stub
		SimulatorConnector.sendCommand("bye");
		MyInterpreter.server.stop();
	}

}
