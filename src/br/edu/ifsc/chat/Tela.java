package br.edu.ifsc.chat;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tela {
	JFrame frame;
	JTable table;
	private MensagemTableModel modelo;
	List<Mensagem> lista;

	public Tela() {
		// Frame initiallization
		frame = new JFrame();
		// Frame Title
		frame.setTitle("Existentes");

		// Initializing the JTable
		table = new JTable(modelo);
		lista = new ArrayList<>();
		atualizar(lista);
		table.setBounds(30, 40, 200, 300);

		// adding it to JScrollPane
		JScrollPane sp = new JScrollPane(table);
		frame.add(sp);
		// Frame Size
		frame.setSize(500, 200);
		// Frame Visible = true
		frame.setVisible(true);
	}

	public void atualizar(List<Mensagem> lista) {
		modelo = new MensagemTableModel(lista);
		table.setModel(modelo);
	}

	public MensagemTableModel getDataTable() {
		return modelo;
	}

}
