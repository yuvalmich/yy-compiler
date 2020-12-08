package interpreter;

public class Program {

	public static void main(String[] args) {
		System.out.println("start");
//		DataReaderServer server = new DataReaderServer();
//		ClientHandler c = new MyClientHandler();
		
//		server.open(5400, c);
		
		String exampleCode = "openDataServer 5400 10\r\n" + 
				"connect 127.0.0.1 5402\r\n" + 
				"var breaks = bind \"/controls/flight/speedbrake\"\r\n" + 
				"var throttle = bind \" /controls/engines/current-engine/throttle\"\r\n" + 
				"var heading = bind \"/instrumentation/heading-indicator/offset-deg\"\r\n" + 
				"var airspeed = bind \"/instrumentation/airspeed-indicator/indicated-speed-kt\"\r\n" + 
				"var roll = bind \"/instrumentation/attitude-indicator/indicated-roll-deg\"\r\n" + 
				"var pitch = bind \"/instrumentation/attitude-indicator/internal-pitch-deg\"\r\n" + 
				"var rudder = bind \"/controls/flight/rudder\"\r\n" + 
				"var aileron = bind \"/controls/flight/aileron\"\r\n" + 
				"var elevator = bind \"/controls/flight/elevator\"\r\n" + 
				"var alt = bind \"/instrumentation/altimeter/indicated-altitude-ft\"\r\n" + 
				"breaks = 0\r\n" + 
				"throttle = 1\r\n" + 
				"rudder = -1\r\n" +
				"var h0 = heading\r\n" + 
				"";
		
		Lexer lexer = new Lexer();
		var expressions = lexer.lexer(exampleCode);
		
		System.out.println(Arrays.toString(expressions));
		
		Parser parser = new Parser(expressions);
		
		parser.parse();
	}
}
