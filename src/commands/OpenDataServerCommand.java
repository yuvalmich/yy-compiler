package commands;

import utils.ClientHandler;
import utils.DataReaderServer;
import utils.MyClientHandler;

public class OpenDataServerCommand implements Command {

	@Override
	public void execute() {
		DataReaderServer server = new DataReaderServer();
		ClientHandler c = new MyClientHandler();
		
		server.open(5400, c);
	}
}
