package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;

import expressions.Executor;
import expressions.Expression;
import utils.SimulatorConnector;

public class ConnectCommand implements Command {

	private BufferedReader in = null;
	private PrintWriter out = null;
	private Socket s = null;
	
	@Override
	public void execute(Callable<String> getNextParam) {
		try {
			String address = getNextParam.call();
			int port = (int) Executor.calc(getNextParam.call());
			
			new Thread(() -> {
				try {
					connect(address, port);
				} catch (Exception e) {e.printStackTrace();}
			}).start();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
	
	public void connect(String address, int port) {
		try{
			s = new Socket(address, port);
			s.setSoTimeout(10000);
			out=new PrintWriter(s.getOutputStream());
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			SimulatorConnector.initConnection(in, out, s);
			
			while(true);
			
		}catch(SocketTimeoutException e){
			System.out.println("\tYour Server takes over 3 seconds to answer");
		}catch(IOException e){
			System.out.println("\tYour Server ran into some IOException");
		}catch(Exception e){
			System.out.println("\texception");
		}
	}
}
