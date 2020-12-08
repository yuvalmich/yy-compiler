package test;

import java.util.Arrays;

import interpreter.Lexer;
import interpreter.Parser;
import utils.Server;
import utils.VarBindings;

public class MyInterpreter {
	public static Server server;
	
	public static  int interpret(String[] lines){
		VarBindings.initVars();
		String convertedCode = String.join("\r\n", lines);
		System.out.println(convertedCode);
		
		Lexer lexer = new Lexer();
		var expressions = lexer.lexer(convertedCode);
		
		System.out.println(Arrays.toString(expressions));
		
		Parser parser = new Parser(expressions);
		
		return parser.parse();
	}
}
