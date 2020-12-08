package utils;

import java.util.HashMap;

public class VarBindings {
	public static HashMap<String, BindValue> programVars;
	public static HashMap<String, BindValue> simVars;
	
	public static void initVars()
	{
		programVars = new HashMap<String, BindValue>();
		simVars = new HashMap<String, BindValue>();
	}
}
