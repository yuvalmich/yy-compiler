package commands;

import java.util.concurrent.Callable;
import utils.BindValue;
import utils.VarBindings;


public class DeclareCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		/*
		 * the command: `var param`
		 * command name: var
		 * request the parameters: String varName
		 * 
		 * Explanation:
		 * if var is already declared, throw exception
		 * else save the var in program vars
		 */
		try {
			String varName = getNextParam.call();
			
			if (VarBindings.programVars.containsKey(varName)) {
				System.out.println("Error! - the var is already declared");
				return;
			}
			
			VarBindings.programVars.put(varName, new BindValue(0));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
