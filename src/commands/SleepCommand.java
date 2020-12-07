package commands;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import expressions.Executor;


public class SleepCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		try {
			int sleepTime = (int) Executor.calc(getNextParam.call());
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
