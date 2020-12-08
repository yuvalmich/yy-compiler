package commands;

import java.util.concurrent.Callable;

import expressions.Expression;
import utils.BindValue;
import utils.VarBindings;

public class BindCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		/*
		 * the command: `x = bind "y"`
		 * command name: bind
		 * request the parameters: String varName, String bindValue
		 * 
		 * Explanation:
		 * if program var is not declared, throw exception
		 * else
		 * 	1. update bindVar simulator value
		 * 	2. creates new simVar with same bindValue
		 */
		
		try {
			String varName = getNextParam.call();
			String bindValue = getNextParam.call();
			
			if (!VarBindings.programVars.containsKey(varName)) {
				System.out.println("Error! - the var you want to bind is not declared");
				return;
			}
			
			BindValue binded = VarBindings.programVars.get(varName);
			binded.sim = bindValue;
			
			VarBindings.simVars.put(bindValue, binded);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
