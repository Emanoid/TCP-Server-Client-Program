package WebSite_Download;
import java.util.Scanner;
import WebSite_Download.Url_downloader;


public class Url_downloader_header {
		
	public static void main(String[] args) throws Exception{
		Scanner Emanoid = new Scanner(System.in);
		System.out.println("Enter host name: ");
		String host = Emanoid.next();
		Emanoid.close();
		//To download and print out a website's header
		//since header is set to true, it prints out only the header
		Url_downloader.downloadsite(host,true);
		
		//Handling websites with different html file names
		if(args.length == 2) { 
			Url_downloader.file = args[1];}
		if (args.length >= 1) {
			host = args[0];}
		else {
			System.out.println("Usage: java WebGrabber host [file]");
			System.exit(-1);
			}
					
	}
}
