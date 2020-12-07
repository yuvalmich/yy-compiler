package commands;

import java.util.concurrent.Callable;

import expressions.Executor;
import expressions.Expression;
import utils.ClientHandler;
import utils.DataReaderServer;
import utils.MyClientHandler;

public class OpenDataServerCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		DataReaderServer server = new DataReaderServer();
		ClientHandler c = new MyClientHandler();
		
		try {
			int port = (int) Executor.calc(getNextParam.call());
			int hrz = (int) Executor.calc(getNextParam.call());
			
			server.open(port, c);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
