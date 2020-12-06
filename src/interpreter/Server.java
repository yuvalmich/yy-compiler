package interpreter;

import java.io.IOException;

public interface Server {
	void open(int port, ClientHandler c);
	void stop();
}
