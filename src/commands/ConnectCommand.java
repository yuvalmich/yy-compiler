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

public class ConnectCommand implements Command {

	@Override
	public void execute(Callable<String> getNextParam) {
		Socket s=null;
		PrintWriter out=null;
		BufferedReader in=null;
		try{
			
			String address = getNextParam.call();
			int port = (int) Executor.calc(getNextParam.call());
			
			
			s = new Socket(address, port);
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
		}catch(Exception e){
			System.out.println("\texception");
		} finally{
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
