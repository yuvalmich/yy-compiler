package interpreter;

import java.util.HashMap;

import commands.AssignCommand;
import commands.BindCommand;
import commands.ConnectCommand;
import commands.DeclareCommand;
import commands.IfCommand;
import commands.OpenDataServerCommand;
import commands.PrintCommand;
import commands.SleepCommand;
import commands.WhileCommand;
import expressions.CommandExpression;
import expressions.Expression;
import expressions.ExpressionsIterator;

public class Parser {
	private ExpressionsIterator iter;
	private HashMap<String, Expression> map;
	private String[] m_Expressions;
	
	public Parser(String[] expressions) {
		m_Expressions = expressions;
		
		this.map = new HashMap<String, Expression>() {{
			put("bind", new CommandExpression(new BindCommand(), iter));
			put("connect", new CommandExpression(new ConnectCommand(), iter));
			put("openDataServer", new CommandExpression(new OpenDataServerCommand(), iter));
			put("print", new CommandExpression(new PrintCommand(), iter));
			put("sleep", new CommandExpression(new SleepCommand(), iter));
			put("=", new CommandExpression(new AssignCommand(), iter));
			put("var", new CommandExpression(new DeclareCommand(), iter));
			put("if", new CommandExpression(new IfCommand(), iter));
			put("while", new CommandExpression(new WhileCommand(), iter));
		}};
	}
	
	public void parse() {
		iter = new ExpressionsIterator(m_Expressions);
		
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
