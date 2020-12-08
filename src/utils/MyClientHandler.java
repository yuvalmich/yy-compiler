package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MyClientHandler implements ClientHandler {

	String[] simNames = {
		"simX", "simY", "simZ"
	};
	
	@Override
	public void handleClient(InputStream input, OutputStream output) {
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)), true);

		try {
			while(true) {
				String[] paramsValues = in.readLine().split(",");
				
				for (int i = 0; i < paramsValues.length; i++) {
					this.updateSimIfBind(this.simNames[i], 	Double.parseDouble(paramsValues[i]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void updateSimIfBind(String simName, double simVal) {
		if (VarBindings.simVars.containsKey(simName)) {
			System.out.println("update: " + simName + " with value " + simVal);
			VarBindings.simVars.get(simName).value = simVal;
		}
	}
}
