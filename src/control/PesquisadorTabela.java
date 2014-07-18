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

public class PesquisadorTabela extends Control
{
	ArrayList<Pesquisador> list = new ArrayList<Pesquisador>();

	public PesquisadorTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(), "Pesquisador"));
		list = new PesquisadorDAO().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("id");
		colunas.add("nome");
		colunas.add("nome_cientifico");
		colunas.add("email");
		colunas.add("sexo");
		colunas.add("classe");
		colunas.add("titulacao");
		colunas.add("curso_vinculado");
		colunas.add("areaformacao");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (Pesquisador pesquisador : list)
		{
			linhas[i] = formatoTabela(pesquisador);
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

	public Object[] formatoTabela(Pesquisador pesquisador)
	{
		return new Object[] { pesquisador.getId(), pesquisador.getNome(),
				pesquisador.getNome_cientifico(), pesquisador.getEmail(),
				pesquisador.getSexo(), pesquisador.getClasse(),
				pesquisador.getTitulacao(),
				pesquisador.getCurso_vinculado().getId(),
				pesquisador.getAreaformacao().getId() };
	}

	public void adc(Pesquisador o) throws Exception
	{
		// list.add(o);
		list = new PesquisadorDAO().listar("");
		preencherTabela();
	}

	public void edt(Pesquisador o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new PesquisadorDAO().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new PesquisadorView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new PesquisadorDAO().remover(list.get(i));
			list = new PesquisadorDAO().listar("");
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
			new PesquisadorView(this, list.get(i));
		}
		catch (Exception e)
		{
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new PesquisadorDAO().listar(x);
		preencherTabela();
	}
}
