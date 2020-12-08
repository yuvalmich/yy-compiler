package commands;

import java.util.concurrent.Callable;

import expressions.Executor;
import interpreter.Parser;

public class IfCommand extends ContidionParser {

	@Override
	public void execute(Callable<String> getNextParam) {
		try {
			String condition = getNextParam.call();
			var conditionExpressions = getConditionExpressions(getNextParam);
			Parser parser = new Parser(conditionExpressions);
			
			if (Executor.calc(condition) == 1)
			{
				parser.parse();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
