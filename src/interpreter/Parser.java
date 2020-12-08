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
import expressions.ExpressionsIterator;

public class Parser {
	private ExpressionsIterator iter;
	private HashMap<String, Expression> map;
	
	Parser(String[] expressions) {
		iter = new ExpressionsIterator(expressions);
		this.map = new HashMap<String, Expression>() {{
			put("bind", new CommandExpression(new BindCommand(), iter));
			put("connect", new CommandExpression(new ConnectCommand(), iter));
			put("openDataServer", new CommandExpression(new OpenDataServerCommand(), iter));
			put("print", new CommandExpression(new PrintCommand(), iter));
			put("sleep", new CommandExpression(new SleepCommand(), iter));
			put("=", new CommandExpression(new AssignCommand(), iter));
			put("var", new CommandExpression(new DeclareCommand(), iter));
			// TODO: change it to condition command after implementation!!!!!!!
			put("if", new CommandExpression(new SleepCommand(), iter));
			put("while", new CommandExpression(new SleepCommand(), iter));
		}};
	}
	
	public void parse() {
		while (iter.hasNext()) {
			String command = this.iter.getNext();
			System.out.println(command);
			Expression exp = map.get(command);
			if (exp == null) {
				System.out.println("Error! could not read command");
				return;
			}

			System.out.println("Execute " + exp.getClass().getName());
			exp.calculate();
		}
	}
}
