package utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class DataReaderServer implements Server {

	private boolean stop;
	
	@Override
	public void open(int port, ClientHandler c) {
		stop = false;

		new Thread(() -> {
			try {
				runServer(port, c);
			} catch (Exception e) {e.printStackTrace();}
		}).start();
	}
	
	private void runServer(int port, ClientHandler clientHandler) throws Exception {
		try {
			MyClientHandler.stop = false;
			ServerSocket server = new ServerSocket(port);
			System.out.println("server is up");
			Socket socket = server.accept();
			System.out.println("client connected");
			clientHandler.handleClient(socket.getInputStream(), socket.getOutputStream());
//			socket.close();
			server.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void stop() {
		MyClientHandler.stop = true;
	}

}
