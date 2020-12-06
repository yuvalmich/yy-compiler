package interpreter;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
	void handleClient(InputStream input, OutputStream output);
}
