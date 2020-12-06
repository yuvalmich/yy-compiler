package interpreter;

public class Lexer {
	public String[] lexer(String code)
	{
		return code.trim().split("\\s+");
	}
}
