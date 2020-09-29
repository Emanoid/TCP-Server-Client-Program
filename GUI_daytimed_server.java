/*
 * Written by: Emmanuel Olatunde
 * Course: CSAS 3211WA
 * Program: GUI_daytimed_server
 * Description: GUI version of daytimed_server
 * 				Takes in port number as input and creates a server at given port number,
 * 				It waits to receive a connection request from client and then 
 * 				sends current date and time to client
 * 				Closes server after one successful connection
 * 				
 * */

package Daytimed;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class GUI_daytimed_server {

	private JFrame frmServerclientPortConnector;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					GUI_daytimed_server window = new GUI_daytimed_server();
					window.frmServerclientPortConnector.setVisible(true);
				    } 
				catch (Exception e) {
					e.printStackTrace();
				    }	}
			});}

	public GUI_daytimed_server() {
		initialize();	}

	private void initialize() {
		frmServerclientPortConnector = new JFrame();
		frmServerclientPortConnector.setTitle("Daytimed Server Program");
		frmServerclientPortConnector.setBounds(100, 100, 575, 392);
		frmServerclientPortConnector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServerclientPortConnector.getContentPane().setLayout(null);
		
		//Label for Port #
		JLabel lblNewLabel = new JLabel("Port #:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(60, 10, 49, 35);
		frmServerclientPortConnector.getContentPane().add(lblNewLabel);
		
		//Text Field for Port #
		textField = new JTextField();
		textField.setBounds(119, 10, 200, 40);
		frmServerclientPortConnector.getContentPane().add(textField);
		textField.setColumns(10);
		
		//Button to Create Server at given port #
		JButton btnNewButton = new JButton("Open Server");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(366, 10, 118, 35);
		frmServerclientPortConnector.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					int port = Integer.parseInt(textField.getText());
					//Terminates connection from server end after connection
					Daytimed.daytimed_server.createserverat(port,false);
					} 
				catch (Exception e2) {
					System.out.println("Failed to connect +"+e2);
					}	}
			});	
				
		//Text Field to output content
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 60, 530, 274);
		frmServerclientPortConnector.getContentPane().add(textArea);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 530, 274);
		frmServerclientPortConnector.getContentPane().add(scrollPane);
		System.setOut(printStream);
		System.setErr(printStream);
		
	}
	
	//Class to channel terminal output to textarea
		public class CustomOutputStream extends OutputStream {
		    private JTextArea textArea;
		     
		    public CustomOutputStream(JTextArea textArea) {
		        this.textArea = textArea;		    }
		     
		    @Override
		    public void write(int b) throws IOException {
		        // redirects data to the text area
		        textArea.append(String.valueOf((char)b));
		        // scrolls the text area to the end of data
		        textArea.setCaretPosition(textArea.getDocument().getLength());
		    }}
}
