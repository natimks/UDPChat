package br.edu.ifsc.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Sender {
	private int port = 5000;
	private MulticastSocket datagramSocket;
	private InetAddress addr;

	public Sender() {
		try {
			datagramSocket = new MulticastSocket(port);
			addr = InetAddress.getByName("225.4.5.6");
			datagramSocket.joinGroup(addr);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void trocarSala(int sala) {
		try {
			datagramSocket.leaveGroup(addr);
			datagramSocket = new MulticastSocket(sala);
			addr = InetAddress.getByName("225.4.5.6");
			datagramSocket.joinGroup(addr);
			port=sala;
			System.out.println("saiu");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg, JTextArea textArea) {
		String msgFinal = "Natalia: "+msg;
		DatagramPacket request = new DatagramPacket(msgFinal.getBytes(), msgFinal.getBytes().length, addr, port);
		// envia request
		try {
			datagramSocket.send(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}