package br.edu.ifsc.chat;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ValidacaoTempo implements Runnable {
	Tela tela;
	private MensagemTableModel modelo;
	List<Mensagem> listaDados;
	private ReentrantLock mutex = new ReentrantLock();

	public ValidacaoTempo(Tela tela) {
		this.tela = tela;
		modelo = tela.getDataTable();
	}

	@Override
	public void run() {
		try {
			mutex.lock();
			while (true) {
				Thread.sleep(1000);
				listaDados = modelo.getAll();
				for (int i = 0; i < listaDados.size(); i++) {
					Mensagem msg = listaDados.get(i);
					msg.tempo -= 1;
					mutex.unlock();
					mutex.lock();
					if (msg.tempo == 0) {
						modelo.removeMensagem(i);
					} else {
						modelo.updateMensagem(i, msg);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.unlock();
		}
	}

}
