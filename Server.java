package Test_Java_01;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected...");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(System.in));

            String inputLine, outputLine;

            while ((inputLine = reader.readLine()) != null) {
                System.out.println("Client: " + inputLine);
                if (inputLine.equals("bye"))
                    break;

                System.out.print("Server: ");
                outputLine = serverReader.readLine();
                writer.println(outputLine);
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
