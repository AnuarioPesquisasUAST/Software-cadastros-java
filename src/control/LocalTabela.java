package control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import modelo.*;
import auxiliares.*;
import dao.*;
import view.*;

public class LocalTabela extends Control
{
	ArrayList<Local> list = new ArrayList<Local>();

	public LocalTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(), "Local"));
		list = new LocalDAO().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("id");
		colunas.add("cidade");
		colunas.add("estado");
		colunas.add("descricao");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (Local local : list)
		{
			linhas[i] = formatoTabela(local);
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

	public Object[] formatoTabela(Local local)
	{
		return new Object[] { local.getId(), local.getCidade(),
				local.getEstado(), local.getDescricao() };
	}

	public void adc(Local o) throws Exception
	{
		// list.add(o);
		list = new LocalDAO().listar("");
		preencherTabela();
	}

	public void edt(Local o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new LocalDAO().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new LocalView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new LocalDAO().remover(list.get(i));
			list = new LocalDAO().listar("");
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
			new LocalView(this, list.get(i));
		}
		catch (Exception e)
		{
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new LocalDAO().listar(x);
		preencherTabela();
	}
}
