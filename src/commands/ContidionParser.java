package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class ContidionParser implements Command {
	public abstract void execute(Callable<String> getNextParam);
	
	String[] getConditionExpressions(Callable<String> getNextParam)
	{
		try {
			List<String> expressions = new ArrayList<String>();
			getNextParam.call();
			var expression = getNextParam.call();
			while (!expression.contentEquals("}"))
			{
				expressions.add(expression);
				expression = getNextParam.call();
			}
		
			String[] expressionsArray = new String[expressions.size()];
			return expressions.toArray(expressionsArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
