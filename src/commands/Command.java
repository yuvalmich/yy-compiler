package commands;

import java.util.concurrent.Callable;

import expressions.Expression;

public interface Command {
	public void execute(Callable<String> getNextParam);
}
