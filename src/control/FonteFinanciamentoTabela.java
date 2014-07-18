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

public class FonteFinanciamentoTabela extends Control
{
	ArrayList<FonteFinanciamento> list = new ArrayList<FonteFinanciamento>();

	public FonteFinanciamentoTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(),
				"Fonte de Financiamento"));
		list = new FonteFinanciamentoDAO().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("id");
		colunas.add("nome");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (FonteFinanciamento fontefinanciamento : list)
		{
			linhas[i] = formatoTabela(fontefinanciamento);
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

	public Object[] formatoTabela(FonteFinanciamento fontefinanciamento)
	{
		return new Object[] { fontefinanciamento.getId(),
				fontefinanciamento.getNome() };
	}

	public void adc(FonteFinanciamento o) throws Exception
	{
		// list.add(o);
		list = new FonteFinanciamentoDAO().listar("");
		preencherTabela();
	}

	public void edt(FonteFinanciamento o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new FonteFinanciamentoDAO().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new FonteFinanciamentoView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new FonteFinanciamentoDAO().remover(list.get(i));
			list = new FonteFinanciamentoDAO().listar("");
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
			new FonteFinanciamentoView(this, list.get(i));
		}
		catch (Exception e)
		{
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new FonteFinanciamentoDAO().listar(x);
		preencherTabela();
	}
}
