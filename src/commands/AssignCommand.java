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
		try {
			String varName = getNextParam.call();
			int varValue = (int) Executor.calc(getNextParam.call());
			
			BindValue b = VarBindings.programVars.get(varName);
			
			b.value = varValue;
			
			if (b.sim != null) {
				SimulatorConnector.sendCommand("set " + b.sim + " " + b.value);
			}
			
			VarBindings.programVars.put(varName, b);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
