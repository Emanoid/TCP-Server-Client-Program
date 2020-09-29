/*
 * Written by: Emmanuel Olatunde
 * Course: CSAS 3211WA
 * Program: daytimed_server
 * Description: A server is created on the given port and the current date
 * 				and time is sent to any client that connects to server
 * 				The server remains opened based on the paremeters passed to the "createserverat" method
 * 				i.e createserverat(port#,true) ==> will create a server at port# and keep the server open 
 * 					createserverat(port#,false) => will create a server at port# and will
 * 												 	close the server after a client successfully
 * 													connects once
 * */

package Daytimed;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class daytimed_server {
	
	//createserverat
	//Purpose: Method to handle the creation of a Server at a given socket and listen for connection 
	//			request from client
	//port: port number
	//keepserveropen: boolean to determine whether to close server after successful connection with client
	public static void createserverat(int port, boolean keepserveropen) {
        try {
        	//Instance of the ServerSocket Class
        	//Purpose: Creates a Server at the given port
            ServerSocket server = new ServerSocket(port);
            
            //To determine the execution of the loop
            boolean running = true;
            
            //Infinite loop to listen for TCP connection request from client provided running = true
            while (running) {
                System.out.println("Server setup, waiting: " + server);
                
                //To listen for any client connection request
                Socket client = server.accept();
                System.out.println("Client connected: " + client);

                //Handles the write-out of the date
                PrintWriter out =
                        new PrintWriter(new
                                OutputStreamWriter(client.getOutputStream()));

                //Date object to return current date & time
                Date today = new Date();
                out.println(today);
                
                //To close TCP connection from client size
                out.close();
                client.close();
                
                if (!keepserveropen) {
                	//To close the TCP connection from server side
                	server.close();
                	System.out.println("Connection to port "+port+" Closed");
                	//terminates the loop
                	running = keepserveropen;
                	}
                else {                
                System.out.println("Connection to port "+port+" is still Opened");
                	}
            }}
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        	}		
	}
	
	public static void main(String[] args) {		
		Scanner Emanoid = new Scanner(System.in);
		System.out.println("Enter port #: ");
		int port = Emanoid.nextInt();	
		Emanoid.close();
		//To open server in given port
		//true to keep server side connection open even if client closes
		createserverat(port, true);
        
    }
}
