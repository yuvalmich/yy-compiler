package expressions;

import commands.Command;

public class CommandExpression implements Expression {
	private Command c;
	
	public void ctor(Command c) {
		this.c = c;
	}
	
	@Override
	public double calculate() {
		c.execute();
		
		// TODO: verify what to return here
		return 0;
	}

}
