package commands;

import java.util.concurrent.Callable;

import expressions.Executor;
import expressions.Expression;
import utils.BindValue;
import utils.SimulatorConnector;
import utils.VarBindings;

public class AssignCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		/*
		 * the command: `x = 5`
		 * command name: =
		 * request the parameters: String varName, String varValue
		 * 
		 * Explanation:
		 * if program var is not declared, throw exception
		 * else
		 * 	1. update bindedVar value
		 * 	2. if sim not empty
		 * 		2.1 call SimulatorConnector with value
		 */
		
		try {
			String varName = getNextParam.call();
			int varValue = (int) Executor.calc(getNextParam.call());
			
			if (!VarBindings.programVars.containsKey(varName)) {
				System.out.println("Error! - the var you want to bind is not declared");
				return;
			}
			
			
			BindValue bindedValue = VarBindings.programVars.get(varName);
			bindedValue.value = varValue;
			
			if (bindedValue.sim != null) {
				SimulatorConnector.sendCommand("set " + bindedValue.sim + " " + bindedValue.value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
