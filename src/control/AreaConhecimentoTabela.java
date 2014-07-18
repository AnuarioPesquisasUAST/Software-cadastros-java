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

public class AreaConhecimentoTabela extends Control
{
	ArrayList<AreaConhecimento> list = new ArrayList<AreaConhecimento>();

	public AreaConhecimentoTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(),
				"Área de Conhecimento"));
		list = new AreaConhecimentoDAO().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("id");
		colunas.add("nome");
		colunas.add("ciencia");
		colunas.add("areaAvaliacao");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (AreaConhecimento areaconhecimento : list)
		{
			linhas[i] = formatoTabela(areaconhecimento);
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

	public Object[] formatoTabela(AreaConhecimento areaconhecimento)
	{
		return new Object[] { areaconhecimento.getId(),
				areaconhecimento.getNome() };
	}

	public void adc(AreaConhecimento o) throws Exception
	{
		// list.add(o);
		list = new AreaConhecimentoDAO().listar("");
		preencherTabela();
	}

	public void edt(AreaConhecimento o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new AreaConhecimentoDAO().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new AreaConhecimentoView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new AreaConhecimentoDAO().remover(list.get(i));
			list = new AreaConhecimentoDAO().listar("");
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
			new AreaConhecimentoView(this, list.get(i));
		}
		catch (Exception e)
		{
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new AreaConhecimentoDAO().listar(x);
		preencherTabela();
	}
}
