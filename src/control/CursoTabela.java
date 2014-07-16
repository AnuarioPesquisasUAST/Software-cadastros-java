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

public class CursoTabela extends Control
{
	ArrayList<Curso> list = new ArrayList<>();

	public CursoTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(), "Curso"));
		list = new CursoMysql().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<>();
		colunas.add("id");
		colunas.add("nome");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (Curso curso : list)
		{
			linhas[i] = formatoTabela(curso);
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

	public Object[] formatoTabela(Curso curso)
	{
		return new Object[] { curso.getId(), curso.getNome() };
	}

	public void adc(Curso o) throws Exception
	{
		// list.add(o);
		list = new CursoMysql().listar("");
		preencherTabela();
	}

	public void edt(Curso o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new CursoMysql().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new CursoView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new CursoMysql().remover(list.get(i));
			list = new CursoMysql().listar("");
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
			new CursoView(this, list.get(i));
		}
		catch (Exception e)
		{
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new CursoMysql().listar(x);
		preencherTabela();
	}
}
