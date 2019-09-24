package br.edu.ifsc.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Sender {
	private int port = 5000;
	private DatagramSocket datagramSocket;
	private InetAddress addr;

	public Sender() {
		try {
			datagramSocket = new DatagramSocket();
			addr = InetAddress.getByName("255.255.255.255");
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg, JTextArea textArea) {
		DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, port);
		// envia request
		try {
			datagramSocket.send(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}