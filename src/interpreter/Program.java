package interpreter;

import java.io.InputStream;
import java.io.OutputStream;

public class Program {

	public static void main(String[] args) {
		System.out.println("start");
		DataReaderServer server = new DataReaderServer();
		ClientHandler c = new MyClientHandler();
		
		server.open(5400, c);

	}

}
