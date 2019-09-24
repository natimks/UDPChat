package br.edu.ifsc.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Sender implements Runnable {
	private int port = 5555;
	private String partner;
	private DatagramSocket datagramSocket;
	private InetAddress addr;

	public Sender(String partner) {
		this.partner = partner;
		try {
			datagramSocket = new DatagramSocket();
			addr = InetAddress.getByName(partner);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
//		while (true) {
//			String msg = sc.nextLine();
//			DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, port);
//			// envia request
//			try {
//				datagramSocket.send(request);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	public void sendMessage(String msg, JTextArea textArea) {
		DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, port);
		// envia request
		try {
			datagramSocket.send(request);
			String text = textArea.getText()+ "\n" + msg;
			textArea.setText(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}