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
		"/instrumentation/airspeed-indicator/indicated-speed-kt",
		"/instrumentation/altimeter/indicated-altitude-ft",
		"/instrumentation/altimeter/pressure-alt-ft",
		"/instrumentation/attitude-indicator/indicated-pitch-deg",
		"/instrumentation/attitude-indicator/indicated-roll-deg",
		"/instrumentation/attitude-indicator/internal-pitch-deg",
		"/instrumentation/attitude-indicator/internal-roll-deg",
		"/instrumentation/encoder/indicated-altitude-ft",
		"/instrumentation/encoder/pressure-alt-ft",
		"/instrumentation/encoder/indicated-altitude-ft",
		"/instrumentation/encoder/pressure-alt-ft",
		"/instrumentation/gps/indicated-altitude-ft",
		"/instrumentation/gps/indicated-ground-speed-kt",
		"/instrumentation/gps/indicated-vertical-speed",
		"/instrumentation/heading-indicator/indicated-heading-deg",
		"/instrumentation/magnetic-compass/indicated-heading-deg",
		"/instrumentation/slip-skid-ball/indicated-slip-skid",
		"/instrumentation/turn-indicator/indicated-turn-rate",
		"/instrumentation/vertical-speed-indicator/indicated-speed-fpm",
		"/controls/flight/aileron",
		"/controls/flight/elevator",
		"/controls/flight/rudder",
		"/controls/flight/flaps",
		"/controls/engines/engine/throttle",
		"/engines/engine/rpm"
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
