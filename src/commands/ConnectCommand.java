package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectCommand implements Command {

	@Override
	public void execute() {
		Socket s=null;
		PrintWriter out=null;
		BufferedReader in=null;
		try{
			s=new Socket("127.0.0.1",5402);
			s.setSoTimeout(10000);
			out=new PrintWriter(s.getOutputStream());
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			out.println("set controls/flight/rudder 1");
			System.out.println("yay");
			out.flush();
			String usol=in.readLine();
			System.out.println(usol);
			
		}catch(SocketTimeoutException e){
			System.out.println("\tYour Server takes over 3 seconds to answer");
		}catch(IOException e){
			System.out.println("\tYour Server ran into some IOException");
		}finally{
			try {
				in.close();
				out.close();
				s.close();
			} catch (IOException e) {
				System.out.println("\tYour Server ran into some IOException");
			}
		}
		
	}

}
