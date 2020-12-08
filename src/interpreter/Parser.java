package interpreter;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import commands.AssignCommand;
import commands.BindCommand;
import commands.ConnectCommand;
import commands.DeclareCommand;
import commands.DisconnectCommand;
import commands.IfCommand;
import commands.OpenDataServerCommand;
import commands.PrintCommand;
import commands.SleepCommand;
import commands.WhileCommand;
import expressions.CommandExpression;
import expressions.Executor;
import expressions.Expression;
import expressions.ExpressionsIterator;

public class Parser {
	private ExpressionsIterator iter;
	private HashMap<String, Expression> map;
	private String[] m_Expressions;
	
	public Parser(String[] expressions) {
		m_Expressions = expressions;
	}
	
	public int parse() {
		iter = new ExpressionsIterator(m_Expressions);
		this.map = new HashMap<String, Expression>() {{
			put("bind", new CommandExpression(new BindCommand(), iter));
			put("connect", new CommandExpression(new ConnectCommand(), iter));
			put("disconnect", new CommandExpression(new DisconnectCommand(), iter));
			put("openDataServer", new CommandExpression(new OpenDataServerCommand(), iter));
			put("print", new CommandExpression(new PrintCommand(), iter));
			put("sleep", new CommandExpression(new SleepCommand(), iter));
			put("=", new CommandExpression(new AssignCommand(), iter));
			put("var", new CommandExpression(new DeclareCommand(), iter));
			put("if", new CommandExpression(new IfCommand(), iter));
			put("while", new CommandExpression(new WhileCommand(), iter));
		}};
		
		
		while (iter.hasNext()) {
			String command = this.iter.getNext();
			System.out.println("execute " + command);
			Expression exp = map.get(command);
			if (command.contentEquals("return"))
			{
				String param = this.iter.getNext();
				return (int) Executor.calc(param);
			}
			if (exp == null) {
				System.out.println("Error! could not read command");
				
			}
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			exp.calculate();
		}
		
		return -1;
	}
}
