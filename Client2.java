package Test_Java_02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

@SuppressWarnings("serial")
public class Client2 extends JFrame {
	private JLabel timeLabel;

	public Client2() {
		setTitle("Clock");
		setSize(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		timeLabel = new JLabel();
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		add(timeLabel, BorderLayout.CENTER);

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTime();
			}
		});
		timer.start();
	}

	private void updateTime() {
		try {
			Socket socket = new Socket("localhost", 12345);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			writer.println("time");
			String time = reader.readLine();
			timeLabel.setText(time);

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Client2().setVisible(true);
			}
		});
	}
}
