/*
 * Written by: Emmanuel Olatunde
 * Course: CSAS 3211WA
 * Program: GUI_daytime_client
 * Description: GUI version of daytimed_client
 * 				Takes in a hostname and a port# as input and creates a client 
 * 				to connect to a server with the given parameters(hostname & port#)
 * 				It is intended to connect to the daytimed_server which then 
 * 				returns the current date and time to this client - provided the 
 * 				connection is successful 
 * 				
 * */
package Daytimed;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;

public class GUI_daytime_client {

	private JFrame frmDaytimedClientProgram;
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					GUI_daytime_client window = new GUI_daytime_client();
					window.frmDaytimedClientProgram.setVisible(true);
					}
				catch (Exception e) {
					e.printStackTrace();
					}	}
			});}

	public GUI_daytime_client() {
		initialize();	}

	private void initialize() {
		frmDaytimedClientProgram = new JFrame();
		frmDaytimedClientProgram.setTitle("Daytimed Client Program");
		frmDaytimedClientProgram.setBounds(100, 100, 649, 388);
		frmDaytimedClientProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDaytimedClientProgram.getContentPane().setLayout(null);
		
		//Label for Hostname
		JLabel lblNewLabel = new JLabel("Hostname: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(23, 10, 62, 31);
		frmDaytimedClientProgram.getContentPane().add(lblNewLabel);
		
		//Lable for Port #
		JLabel lblPort = new JLabel("Port #:");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPort.setBounds(252, 10, 62, 31);
		frmDaytimedClientProgram.getContentPane().add(lblPort);
		
		//TextField for Hostname
		textField = new JTextField();
		textField.setBounds(94, 10, 135, 31);
		frmDaytimedClientProgram.getContentPane().add(textField);
		textField.setColumns(10);
		
		//Textfield for Port #
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(305, 11, 109, 31);
		frmDaytimedClientProgram.getContentPane().add(textField_1);
				
		//Button to Connect to server
		JButton btnNewButton = new JButton("Connect to Server");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(436, 10, 189, 37);
		frmDaytimedClientProgram.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					//Gets hostname
					String hostname = textField.getText();
					//Gets port #
					int port = Integer.parseInt(textField_1.getText());
					//Creates Client to connect to Server at: hostname & port #
					Daytimed.daytime_client.createclient(hostname, port);					
					} 
				catch (Exception e2) {
					System.out.println("Failed to connect +"+e2);
					}	}
			});
		
		//Text Area to output result
		JTextArea textArea = new JTextArea();
		textArea.setBounds(23, 63, 584, 278);
		frmDaytimedClientProgram.getContentPane().add(textArea);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		System.setOut(printStream);
		System.setErr(printStream);
		
		//Handles Scrolling
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(605, 63, -579, 278);
		frmDaytimedClientProgram.getContentPane().add(scrollPane);
		
	}
	
	//Class to channel terminal output to textarea
			public class CustomOutputStream extends OutputStream {
			    private JTextArea textArea;
			     
			    public CustomOutputStream(JTextArea textArea) {
			        this.textArea = textArea;
			    }
			     
			    @Override
			    public void write(int b) throws IOException {
			        // redirects data to the text area
			        textArea.append(String.valueOf((char)b));
			        // scrolls the text area to the end of data
			        textArea.setCaretPosition(textArea.getDocument().getLength());
			    }}
}
