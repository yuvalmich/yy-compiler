package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimulatorConnector {
	private static BufferedReader in = null;
	private static PrintWriter out = null;
	private static Socket s = null;
	private static boolean isInitialized = false;

	
	public static void initConnection(BufferedReader in, PrintWriter out, Socket s) {
		SimulatorConnector.in = in;
		SimulatorConnector.out = out;
		SimulatorConnector.s = s;
		SimulatorConnector.isInitialized = true;
	}
	
	public static void sendCommand(String command) {
		try {
			if (isInitialized) {
				// example: "set controls/flight/rudder 1"
				out.println(command);
				//System.out.println("yay");
				out.flush();
			} else {
				System.out.println("error - socket is closed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
