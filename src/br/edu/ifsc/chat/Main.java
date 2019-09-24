package br.edu.ifsc.chat;

import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Tela tela = new Tela();
				new Thread(new Receiver(tela)).start();
			}
		});
	}
}
