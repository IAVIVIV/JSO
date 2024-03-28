package Test_Java_02;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server2 {
	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(12345);
			System.out.println("Server started...");

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected...");

				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

				String inputLine;
				while ((inputLine = reader.readLine()) != null) {
					if (inputLine.equals("time")) {
						SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
						Date date = new Date();
						String response = formatter.format(date);
						writer.println(response);
					}
				}
				clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
