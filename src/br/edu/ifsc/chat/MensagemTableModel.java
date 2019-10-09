package br.edu.ifsc.chat;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MensagemTableModel extends AbstractTableModel {

	private static final int COL_TEXTO = 0;
	private static final int COL_TEMPO = 1;
	private static final int COL_IP = 2;

	List<Mensagem> linhas;
	private String[] colunas = new String[] { "Mensagem", "Tempo restante", "IP" };

	public MensagemTableModel(List<Mensagem> mensagens) {
		this.linhas = new ArrayList<>(mensagens);
	}

	public List<Mensagem> getAll(){
		return linhas;
	}
	public int getRowCount() {
		return linhas.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Class getColumnClass(int columnIndex) {
		if (columnIndex == COL_IP) {
			return Integer.class;
		}
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int row, int column) {

		Mensagem m = linhas.get(row);

		if (column == COL_TEXTO) {
			return m.getTexto();
		} else if (column == COL_TEMPO) {
			return m.getTempo();
		} else if (column == COL_IP) {
			return m.getIP();
		}
		return "";
	}

	public void setValueAt(Object aValue, int row, int column) {
		Mensagem u = linhas.get(row);
		if (column == COL_TEXTO) {
			u.setTexto(aValue.toString());
		} else if (column == COL_TEMPO) {
			u.setTempo((Integer) aValue);
		} else if (column == COL_IP) {
			u.setIp(aValue.toString());
		}
	}

	public Mensagem getMensagem(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	public void addMensagem(Mensagem mensagem) {
		linhas.add(mensagem);
		int ultimoIndice = getRowCount() - 1;
		System.out.println("UPDATE INDICE "+ mensagem.getIP()+" tempo> "+ mensagem.getTempo());
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void updateMensagem(int indiceLinha, Mensagem msg) {
		linhas.set(indiceLinha, msg);
		System.out.println("UPDATE INDICE "+ msg.getIP()+" tempo> "+ msg.getTempo());
		fireTableRowsUpdated(indiceLinha, indiceLinha);
	}

	public void removeMensagem(int indiceLinha) {
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}
