package control;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.PalavraChave;
import view.PalavraChaveView;
import auxiliares.Control;
import dao.PalavraChaveDAO;

public class PalavraChaveTabela extends Control
{
	ArrayList<PalavraChave> list = new ArrayList<PalavraChave>();

	public PalavraChaveTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(), "Palavra-chave"));
		list = new PalavraChaveDAO().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("id");
		colunas.add("palavra");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (PalavraChave palavrachave : list)
		{
			linhas[i] = formatoTabela(palavrachave);
			i++;
		}
		view.getTabela().setModel(
				new DefaultTableModel(linhas, colunas.toArray()));
		if (view.getOpcoes().getModel().getSize() == 0)
			view.getOpcoes().setModel(
					new DefaultComboBoxModel(colunas.toArray()));
		for (int x = 0; x < view.getTabela().getColumnCount(); x++)
		{
			String columnName = view.getTabela().getColumnName(x);
			TableColumn col = view.getTabela().getColumnModel().getColumn(x);
			col.setMinWidth(new JLabel(columnName).getPreferredSize().width + 10);
		}
	}

	public Object[] formatoTabela(PalavraChave palavrachave)
	{
		return new Object[] { palavrachave.getId(), palavrachave.getPalavra() };
	}

	public void adc(PalavraChave o) throws Exception
	{
		// list.add(o);
		list = new PalavraChaveDAO().listar("");
		preencherTabela();
	}

	public void edt(PalavraChave o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new PalavraChaveDAO().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new PalavraChaveView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new PalavraChaveDAO().remover(list.get(i));
			list = new PalavraChaveDAO().listar("");
			preencherTabela();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	public synchronized void editar(int i)
	{
		try
		{
			new PalavraChaveView(this, list.get(i));
		}
		catch (Exception e)
		{
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new PalavraChaveDAO().listar(x);
		preencherTabela();
	}
}
