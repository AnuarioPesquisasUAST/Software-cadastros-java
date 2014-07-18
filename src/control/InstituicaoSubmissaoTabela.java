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

public class InstituicaoSubmissaoTabela extends Control
{
	ArrayList<InstituicaoSubmissao> list = new ArrayList<InstituicaoSubmissao>();

	public InstituicaoSubmissaoTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(),
				"Instituição de Submissão"));
		list = new InstituicaoSubmissaoDAO().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("id");
		colunas.add("nome");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (InstituicaoSubmissao instituicaosubmissao : list)
		{
			linhas[i] = formatoTabela(instituicaosubmissao);
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

	public Object[] formatoTabela(InstituicaoSubmissao instituicaosubmissao)
	{
		return new Object[] { instituicaosubmissao.getId(),
				instituicaosubmissao.getNome() };
	}

	public void adc(InstituicaoSubmissao o) throws Exception
	{
		// list.add(o);
		list = new InstituicaoSubmissaoDAO().listar("");
		preencherTabela();
	}

	public void edt(InstituicaoSubmissao o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new InstituicaoSubmissaoDAO().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new InstituicaoSubmissaoView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new InstituicaoSubmissaoDAO().remover(list.get(i));
			list = new InstituicaoSubmissaoDAO().listar("");
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
			new InstituicaoSubmissaoView(this, list.get(i));
		}
		catch (Exception e)
		{
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new InstituicaoSubmissaoDAO().listar(x);
		preencherTabela();
	}
}
