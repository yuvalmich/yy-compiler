package expressions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Executor {
	public static double calc(String[] expressions){
		Queue<String> queue = new LinkedList<String>();
		Stack<String> stack = new Stack<String>();
		Stack<Expression> stackExp = new Stack<Expression>();
		
		for (String exp : expressions){
			if (isDouble(exp)){
				queue.add(exp);
			}
			else{
				switch(exp) {
			    case "/":
			    case "*":
			    case "(":
			        stack.push(exp);
			        break;
			    case "+":
			    case "-":
			    	while (!stack.empty() && (!stack.peek().equals("("))){
			    		queue.add(stack.pop());
			    	}
			        stack.push(exp);
			        break;
			    case ")":
			    	while (!stack.peek().equals("(")){
			    		queue.add(stack.pop());
			    	}
			    	stack.pop();
			        break;
				}
			}
		}
		while(!stack.isEmpty()){
			queue.add(stack.pop());
		}
		
		for(String str : queue) {
			if (isDouble(str)){
				stackExp.push(new Number(Double.parseDouble(str)));
			}
			else{
				Expression right = stackExp.pop();
				Expression left = stackExp.pop();
				
				switch(str) {
			    case "/":
			    	stackExp.push(new Div(left, right));
			        break;
			    case "*":
			    	stackExp.push(new Mul(left, right));
			        break;
			    case "+":
			    	stackExp.push(new Plus(left, right));
			        break;
			    case "-":
			    	stackExp.push(new Minus(left, right));
			        break;
				}
			}
		}
	
		return Math.floor((stackExp.pop().calculate() * 1000)) /1000;
	}
	
	private static boolean isDouble(String val){
		try {
		    Double.parseDouble(val);
		    return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
