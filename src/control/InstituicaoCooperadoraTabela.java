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

public class InstituicaoCooperadoraTabela extends Control
{
	ArrayList<InstituicaoCooperadora> list = new ArrayList<InstituicaoCooperadora>();

	public InstituicaoCooperadoraTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(),
				"Instituição Cooperadora"));
		list = new InstituicaoCooperadoraDAO().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("id");
		colunas.add("nome");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (InstituicaoCooperadora instituicaocooperadora : list)
		{
			linhas[i] = formatoTabela(instituicaocooperadora);
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

	public Object[] formatoTabela(InstituicaoCooperadora instituicaocooperadora)
	{
		return new Object[] { instituicaocooperadora.getId(),
				instituicaocooperadora.getNome() };
	}

	public void adc(InstituicaoCooperadora o) throws Exception
	{
		// list.add(o);
		list = new InstituicaoCooperadoraDAO().listar("");
		preencherTabela();
	}

	public void edt(InstituicaoCooperadora o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new InstituicaoCooperadoraDAO().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new InstituicaoCooperadoraView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new InstituicaoCooperadoraDAO().remover(list.get(i));
			list = new InstituicaoCooperadoraDAO().listar("");
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
			new InstituicaoCooperadoraView(this, list.get(i));
		}
		catch (Exception e)
		{
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new InstituicaoCooperadoraDAO().listar(x);
		preencherTabela();
	}
}
