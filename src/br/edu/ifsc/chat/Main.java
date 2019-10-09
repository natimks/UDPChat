package br.edu.ifsc.chat;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Tela tela = new Tela();
				Receiver receiver = new Receiver(tela, 2055);
				Thread t = new Thread(receiver);
				t.start();
				Sender sender = new Sender();
				Thread senderThread = new Thread(sender);
				senderThread.start();
				ValidacaoTempo validacaoTempo = new ValidacaoTempo(tela);
				Thread validacaoThread = new Thread(validacaoTempo);
				validacaoThread.start();
			}
		});
	}
}
