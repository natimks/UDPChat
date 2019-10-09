package br.edu.ifsc.chat;

public class Mensagem {
	String ip;
	String texto;
	int tempo;

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getIP() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Mensagem(String texto, int tempo, String ip) {
		this.tempo = tempo;
		this.texto = texto;
		this.ip = ip;
	}
}
