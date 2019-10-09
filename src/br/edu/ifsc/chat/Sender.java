package br.edu.ifsc.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Sender implements Runnable {
	private int port = 2055;
	private MulticastSocket datagramSocket;
	private InetAddress addr;

	public Sender() {
		try {
			datagramSocket = new MulticastSocket(port);
			addr = InetAddress.getByName("225.4.5.7");
			datagramSocket.joinGroup(addr);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String msg= "Existo";
		DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, port);
		try {
			while (true) {
				Thread.sleep(1000);
				datagramSocket.send(request);
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}finally {
			
		}
	}
}