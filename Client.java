package Test_Java_01;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 12345);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

			String inputLine, outputLine;

			System.out.print("Client: ");
			while ((outputLine = consoleReader.readLine()) != null) {
				writer.println(outputLine);
				if (outputLine.equals("bye"))
					break;

				inputLine = reader.readLine();
				System.out.println("Server: " + inputLine);

				System.out.print("Client: ");
			}

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
