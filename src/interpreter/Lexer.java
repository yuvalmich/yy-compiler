package interpreter;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	
	// Data Members
	private List<String> m_ExpressionsList = new ArrayList<String>();
	private String m_Expression;
	private List<String> m_Commands;
	
	public Lexer()
	{
		m_Commands = new ArrayList<String>();
		m_Commands.add("bind");
		m_Commands.add("connect");
		m_Commands.add("openDataServer");
		m_Commands.add("print");
		m_Commands.add("sleep");
		m_Commands.add("=");
		m_Commands.add("var");
		m_Commands.add("if");
		m_Commands.add("while");
	}
	
	public String[] lexer(String code)
	{
		var splits = code.trim().split("\\s+");
		
		for (String split : splits) {
			initialize(split);
		}
		
		m_ExpressionsList.add(m_Expression);
		
		String[] expressionsArray = new String[m_ExpressionsList.size()];
		
		return m_ExpressionsList.toArray(expressionsArray);
	}
	
	private void initialize(String s)
	{
		if (m_Expression != null && !m_Expression.isEmpty())
		{
			var endCharExpression = m_Expression.charAt(m_Expression.length() - 1);
			if (!isOperator(s.charAt(0)) && (endCharExpression == ')' || !isOperator(endCharExpression)))
			{
				m_ExpressionsList.add(m_Expression);
				m_Expression = "";
			}
		}
		else
		{
			m_Expression = "";
		}
		
		int endIndex = s.length();
		String subString = s;
		
		boolean isSubCommand = isCommand(subString);
		
		while (endIndex > 1 && !isSubCommand)
		{
			subString = s.substring(0, --endIndex);
			isSubCommand = isCommand(subString);
		}
		
		if (endIndex == 1 && !isSubCommand)
		{
			m_Expression = m_Expression.concat(s);
			return;
		}
		
		if (isSubCommand)
		{
			if (!m_Expression.isEmpty())
			{
				m_ExpressionsList.add(m_Expression);
			}
			
			m_ExpressionsList.add(subString);
			m_Expression = "";
		}
		else
		{
			m_Expression = m_Expression.concat(subString);
		}
		
		if (endIndex < s.length()) 
		{
			initialize(s.substring(endIndex));
		}
	}
	
	private boolean isOperator(char c)
	{
		// handle == !!!!!
		return "/*()+-–<>".indexOf(c) != -1;
	}
	
	private boolean isCommand(String val)
	{
		return m_Commands.contains(val);
	}
}
