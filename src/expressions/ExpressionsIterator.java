package expressions;

public class ExpressionsIterator {
	String[] expressions;
	int index;
	
	public ExpressionsIterator(String[] expressions) {
		this.expressions = expressions;
		index = 0;
	}
	
	public boolean hasNext() {
		return index < this.expressions.length - 1;
	}
	
	public String getNext() {
		return this.expressions[index++];
	}
	
	public String peekNext() {
		return this.expressions[index];
	}
	
	
	// for case = when the parameter before the command.
	// now the index is after the first parameter in the = char
	// example: [x, =, 5] (the parser is in =)
	// the function will replace the first param and = order
	public String HandleAssign() {
		index--;
		String varName = this.expressions[index];
		this.expressions[index] = this.expressions[index + 1]; // put = before varName
		this.expressions[index + 1] = varName;
		
		return this.getNext();
	}
}
