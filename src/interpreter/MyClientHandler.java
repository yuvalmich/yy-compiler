package interpreter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class MyClientHandler implements ClientHandler {

	@Override
	public void handleClient(InputStream input, OutputStream output) {
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)), true);
		try {
			HashMap<String, Object> data = new HashMap<String, Object>();
			String line;
			while(true) {
				line = in.readLine();
				System.out.println(line);
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

}
