package expressions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Stream;

import utils.VarBindings;

public class Executor {
	static HashMap<String, String> parameters;
	
	public static double calc(String expression){
		Queue<String> queue = new LinkedList<String>();
		Stack<String> stack = new Stack<String>();
		Stack<Expression> stackExp = new Stack<Expression>();
		
		String[] split = expression.split("(?<=[-+*/()<>])|(?=[-+*/()<>])");
		if (split[0].contentEquals("-"))
		{
			String[] arr = {"0"};
			split = Stream.concat(Arrays.stream(arr), Arrays.stream(split)).toArray(String[]::new);
		}
		for (String s : split){
			if (isDouble(s)){
				queue.add(s);
			}
			if (isParameter(s))
			{
				queue.add(String.valueOf(VarBindings.programVars.get(s).value));
			}
			else{
				switch(s) {
			    case "/":
			    case "*":
			    case "<":
			    case ">":
			    case "(":
			        stack.push(s);
			        break;
			    case "+":
			    case "-":
			    	while (!stack.empty() && (!stack.peek().equals("("))){
			    		queue.add(stack.pop());
			    	}
			        stack.push(s);
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
			    case "<":
			    	stackExp.push(new Smaller(left, right));
			        break;
			    case ">":
			    	stackExp.push(new Bigger(left, right));
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
	
	private static boolean isParameter(String val)
	{
		return VarBindings.programVars.containsKey(val);
	}
}
