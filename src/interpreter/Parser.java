package interpreter;

import java.util.HashMap;

import commands.BindCommand;
import commands.ConnectCommand;
import commands.OpenDataServerCommand;
import commands.SetCommand;
import expressions.CommandExpression;
import expressions.Expression;

public class Parser {
	private HashMap<String, Expression> map = new HashMap<String, Expression>() {{
		put("bind", new CommandExpression(new BindCommand()));
		put("bind", new CommandExpression(new ConnectCommand()));
		put("openDataServer", new CommandExpression(new OpenDataServerCommand()));
		put("bind", new CommandExpression(new SetCommand()));
	}};
	
	public void parse(String[] lexerOutput) {
		int index = 0;
		Expression exp = map.get(lexerOutput[index]);
		
		while (index < lexerOutput.length) {
			if (exp == null) {
				System.out.println("Error! could not read command");
				return;
			}
			
			index += exp.calculate();
		}
	}
}
