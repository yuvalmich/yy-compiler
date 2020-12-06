package commands;

import interpreter.ClientHandler;
import interpreter.DataReaderServer;
import interpreter.MyClientHandler;

public class OpenDataServer implements Command {

	@Override
	public void execute() {
		DataReaderServer server = new DataReaderServer();
		ClientHandler c = new MyClientHandler();
		
		server.open(5400, c);
	}

}
