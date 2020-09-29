/*
 * Written by: Emmanuel Olatunde
 * Course: CSAS 3211WA
 * Program: daytimed_client
 * Description: A client is created to connect to a given server based on its hostname and port#.
 * 				This client is meant to connect to the daytimed_server program which
 * 				will return the current  time and date once this client connects to it
 * */

package Daytimed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class daytime_client {
	
	//createclient
	//Purpose: Method to handle the creation of Client
	//hostname: hostname of server to connect to
	//port: port number of server to connect to
	public static void createclient(String hostname, int port) throws IOException {
		
		//Initialize Client, input and output handlers
		Socket Client = null;
	    PrintWriter out = null;
	    BufferedReader in = null;
	
	    try {
	    	//Creates a Socket at Client end
	        Client = new Socket(hostname, port);
	        //Send connection Request
	        out = new PrintWriter(Client.getOutputStream(), true);
	        //Gets return value after connecting to Server
	        in = new BufferedReader(new InputStreamReader(Client.getInputStream()));
	    	} 
	    catch (IOException e) {
	        System.out.println(e);
	        return;
	    	}
	    
	    //Handles return value from Server
	    out.println("ping");
	    System.out.println(in.readLine());
	    out.close();
	    in.close();
	    //Closes Client End
	    Client.close();
	}
	
	public static void main(String[] args) throws IOException {	
		Scanner Emanoid = new Scanner(System.in);
		System.out.println("Enter server hostname: ");
		String hostname = Emanoid.next();
		System.out.println("Enter server port #: ");
		int port = Emanoid.nextInt();	
		Emanoid.close();
		
		//To open a client to connect to a server with
		// a given hostname & port #
	    createclient(hostname,port);
	}
	

}
