package br.edu.ifsc.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Receiver implements Runnable {
	private MulticastSocket datagramSocket;
	private InetAddress inetAddress;
	private ReentrantLock mutex = new ReentrantLock();
	private Tela tela;
	private MensagemTableModel modelo;
	private List<Mensagem> listaDados;

	public Receiver(Tela tela, int porta) {
		try {
			datagramSocket = new MulticastSocket(porta);
			inetAddress = InetAddress.getByName("225.4.5.7");
			datagramSocket.joinGroup(inetAddress);
			this.tela = tela;
			modelo = tela.getDataTable();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			mutex.lock();
			while (true) {
				byte buffer[] = new byte[1000];
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				datagramSocket.receive(request);
				String msg = new String(request.getData());
				String ip = request.getAddress().getHostAddress();
				List<Mensagem> todasMensagens = modelo.getAll();
				boolean existe = false;
				int posicaoExistente = 0;
				for (int i = 0; i < todasMensagens.size(); i++) {
					if (todasMensagens.get(i).ip.equals(ip)) {
						existe = true;
						posicaoExistente = i;
					}
				}
				mutex.unlock();
				mutex.lock();
				if (existe) {
					modelo.updateMensagem(posicaoExistente, new Mensagem(msg, 3, ip));
				} else {
					modelo.addMensagem(new Mensagem(msg, 3, ip));
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			mutex.unlock();
		}
	}

	public void closeSocket() {
		datagramSocket.close();
	}

}