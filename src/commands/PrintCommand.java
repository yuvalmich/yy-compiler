package commands;

import java.util.concurrent.Callable;

import expressions.Expression;

public class PrintCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		try {
			String message = getNextParam.call();
			
			System.out.println(message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
