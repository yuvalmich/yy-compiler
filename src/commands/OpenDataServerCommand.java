package commands;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import expressions.Executor;
import expressions.Expression;
import test.MyInterpreter;
import utils.ClientHandler;
import utils.DataReaderServer;
import utils.MyClientHandler;

public class OpenDataServerCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		MyInterpreter.server = new DataReaderServer();
		ClientHandler c = new MyClientHandler();
		
		try {
			int port = (int) Executor.calc(getNextParam.call());
			int hrz = (int) Executor.calc(getNextParam.call());
			
			MyInterpreter.server.open(port, c);
			
//			System.out.println("going to sleep");
//			TimeUnit.SECONDS.sleep(20);
//			System.out.println("good morning");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
