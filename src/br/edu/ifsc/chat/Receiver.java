package br.edu.ifsc.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Receiver implements Runnable {
	private int port = 5000;
	private MulticastSocket datagramSocket;
	InetAddress inetAddress;
	Tela tela;

	public Receiver(Tela tela,int porta) {
		try {
			datagramSocket = new MulticastSocket(porta);
			inetAddress = InetAddress.getByName("225.4.5.6");
			datagramSocket.joinGroup(inetAddress);
			this.tela = tela;
			this.port=porta;
			
		} catch (SocketException e) {
		//	e.printStackTrace();
		} catch (IOException e) {
		//	e.printStackTrace();
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
			//	e.printStackTrace();
			}
		}
	}
	
	public void closeSocket() {
		datagramSocket.close();
	}

}