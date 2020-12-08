package commands;

import java.util.List;
import java.util.concurrent.Callable;

public interface ContidionParser {
	
	public void execute(String condition, List<Command> commands, Callable<String> getNextParam);
}
