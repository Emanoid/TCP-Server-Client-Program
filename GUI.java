package WebSite_Download;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import WebSite_Download.Url_downloader;
import javax.swing.JLabel;

public class GUI {

	private JFrame frmWebGrabber;
	private JTextField textField;
	public String host;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmWebGrabber.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWebGrabber = new JFrame();
		frmWebGrabber.setTitle("Web Grabber - Emmanuel Olatunde");
		frmWebGrabber.setBounds(100, 100, 796, 598);
		frmWebGrabber.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWebGrabber.getContentPane().setLayout(null);
		
		//Button to Download entire Website
		JButton btnNewButton = new JButton("Download Site");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				host = textField.getText();
				//JOptionPane.showMessageDialog(null,host);
				try {
					Url_downloader.downloadsite(host,false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnNewButton.setBounds(21, 58, 157, 30);
		frmWebGrabber.getContentPane().add(btnNewButton);
		
		//Button to Downlaod Website Header
		JButton btnNewButton_1 = new JButton("Download Site Header");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				host = textField.getText();
				//JOptionPane.showMessageDialog(null,host);
				try {
					Url_downloader.downloadsite(host,true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnNewButton_1.setBounds(237, 58, 240, 30);
		frmWebGrabber.getContentPane().add(btnNewButton_1);
		
		//Label for Host
		JLabel lblNewLabel = new JLabel("Enter Host:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 10, 92, 25);
		frmWebGrabber.getContentPane().add(lblNewLabel);
		
		//Textfield to gtet the name of host
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField.setBounds(75, 10, 449, 38);
		frmWebGrabber.getContentPane().add(textField);
		textField.setColumns(10);
		
		//Scrollpane for Application
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 108, 723, 430);
		frmWebGrabber.getContentPane().add(scrollPane);
		
		//TextArea for Application to insert output
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));		
		System.setOut(printStream);
		System.setErr(printStream);
		
		


	}
}
