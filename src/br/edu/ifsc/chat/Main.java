package br.edu.ifsc.chat;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o numero de IP de seu parceiro: ");
		String ip = sc.nextLine();
		new Thread(new Receiver()).start();
		new Thread(new Sender(ip)).start();
	}
}
