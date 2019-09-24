package br.edu.ifsc.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class Receiver implements Runnable {
	private int port = 5555;
	private DatagramSocket datagramSocket;
	Tela tela;

	public Receiver(Tela tela) {
		try {
			datagramSocket = new DatagramSocket(port);
			this.tela=tela;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			byte buffer[] = new byte[1000];
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			try {
				datagramSocket.receive(request);
				String msg = new String(request.getData());
				tela.setText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}