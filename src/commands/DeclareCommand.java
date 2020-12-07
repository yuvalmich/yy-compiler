package commands;

import java.util.concurrent.Callable;
import utils.BindValue;
import utils.VarBindings;


public class DeclareCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		try {
			String varName = getNextParam.call();
			
			VarBindings.programVars.put(varName, new BindValue(0));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
