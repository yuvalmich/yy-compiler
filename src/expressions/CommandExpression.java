package expressions;

import commands.Command;

public class CommandExpression implements Expression {
	private Command c;
	private ExpressionsIterator iter;
	
	public CommandExpression(Command c, ExpressionsIterator iter) {
		this.c = c;
		this.iter = iter;
	}
	
	@Override
	public double calculate() {
		c.execute(() -> this.iter.getNext());
		
		// TODO: verify what to return here
		return 0;
	}

}
