package commands;

import java.util.concurrent.Callable;

import expressions.Expression;
import utils.ClientHandler;
import utils.DataReaderServer;
import utils.MyClientHandler;

public class OpenDataServerCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		DataReaderServer server = new DataReaderServer();
		ClientHandler c = new MyClientHandler();
		
		server.open(5400, c);
	}
}
