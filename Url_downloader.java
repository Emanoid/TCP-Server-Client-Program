package WebSite_Download;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Url_downloader {
	
	//the port to use for the connection
	public static int port = 80;
	//the file to get
	public static String file = "/";	
	
	//Methods
	//send: a PrintWriter, String -> print msg
	//Purpose: to properly terminate a 'line' of characters to send out
	//         makes the program execute similarly on Unix & Windows devices
	public static void send(PrintWriter out, String msg) {
		out.print(msg+"\r\n");
	}	
		
	//sendHTTPCode: PrintWriter, String, String -> void
	//Purpose: To send HTTP GET request to host
	private static void sendHTTPCode(PrintWriter out, String file, String host) {
		send(out, "GET "+file+"HTTP/1.1");
		send(out, "HOST: "+host);
		send(out, "");
		out.flush();
	}
	
	//receiveHTTPData: BufferedReader
	//Purpose: To print out lines of webpage 
	public static void receiveHTTPData(BufferedReader in) throws Exception{
		String line = in.readLine();
		do {
			System.out.println("Got: "+line);
			line = in.readLine();
		}
		while(line != null);
	}

	//receiveHTTPHeader: BufferedReader
	//Purpose: To print out lines of webpage Header
	public static void receiveHTTPHeader(BufferedReader in) throws Exception{
		String line = in.readLine();
		while(line != null && line.isBlank() == false) {
			System.out.println("Got: "+line);
			line = in.readLine();			
		}
	}
	
	//downloadsite: host, boolean -> site source code
	//Purpose: To print out the source code of the given host
	//If header == true -> it prints our only the header
	//If header == false -> it prints our the entire webpage
	public static void downloadsite(String host, boolean header) throws Exception{
		//instantiating socket
		Socket s = new Socket(host, port);
				
		try {
			//Ensuring Socket is active
			System.out.println("Connecting to "+host+" on port "+port);
			System.out.println("Connection established: \n"+s);
			}
		catch(Exception e) {
			//Prints error if socket is not active
			System.err.println("Error: "+ e);
			}
				
		//Setting up input and output streams of characters
		System.out.println("Setting up input and output streams of characters");
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			
		//To request HTTP file
		sendHTTPCode(out, file, host);
		//To parse HTTP file contents and print out
		if (header != true) {
			receiveHTTPData(in);}
		else {
			receiveHTTPHeader(in);
		}
				
		//Disconnecting Socket, input and output streams
		in.close();
		out.close();
		s.close();
		System.out.println("Disconnected");
							
	}
	
	//Main 
	public static void main(String[] args) throws Exception {
		
		Scanner Emanoid = new Scanner(System.in);
		//host to connect to
		System.out.println("Enter the host: ");
		String host = Emanoid.next();
		Emanoid.close();
		downloadsite(host,false);
			
		//Handling websites with different html file names
		if(args.length == 2) { 
			file = args[1];}
		if (args.length >= 1) {
			host = args[0];}
		else {
			System.out.println("Usage: java WebGrabber host [file]");
			System.exit(-1);
			}
		}	
	
}
