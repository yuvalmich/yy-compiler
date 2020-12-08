package test;

import java.util.Arrays;

import interpreter.Lexer;
import interpreter.Parser;

public class MyInterpreter {

	public static  int interpret(String[] lines){
		String convertedCode = String.join("\r\n", lines);
		
		
		Lexer lexer = new Lexer();
		var expressions = lexer.lexer(convertedCode);
		
		System.out.println(Arrays.toString(expressions));
		
		Parser parser = new Parser(expressions);
		
		return parser.parse();
	}
}
