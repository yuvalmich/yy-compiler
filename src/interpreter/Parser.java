package interpreter;

import java.util.HashMap;

import commands.AssignCommand;
import commands.BindCommand;
import commands.ConnectCommand;
import commands.DeclareCommand;
import commands.OpenDataServerCommand;
import commands.PrintCommand;
import commands.SleepCommand;
import expressions.CommandExpression;
import expressions.Expression;

public class Parser {
	private HashMap<String, Expression> map = new HashMap<String, Expression>() {{
		put("bind", new CommandExpression(new BindCommand()));
		put("connect", new CommandExpression(new ConnectCommand()));
		put("openDataServer", new CommandExpression(new OpenDataServerCommand()));
		put("print", new CommandExpression(new PrintCommand()));
		put("sleep", new CommandExpression(new SleepCommand()));
		put("=", new CommandExpression(new AssignCommand()));
		put("var", new CommandExpression(new DeclareCommand()));
		// TODO: change it to condition command after implementation!!!!!!!
		put("if", new CommandExpression(new SleepCommand()));
		put("while", new CommandExpression(new SleepCommand()));
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
