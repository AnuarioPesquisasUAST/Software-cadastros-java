package control;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.AreaFormacao;
import view.AreaFormacaoView;
import auxiliares.Control;
import dao.AreaFormacaoDAO;

public class AreaFormacaoTabela extends Control
{
	ArrayList<AreaFormacao> list = new ArrayList<AreaFormacao>();

	public AreaFormacaoTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(), "Área de Formação"));
		list = new AreaFormacaoDAO().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("id");
		colunas.add("nome");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (AreaFormacao areaformacao : list)
		{
			linhas[i] = formatoTabela(areaformacao);
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

	public Object[] formatoTabela(AreaFormacao areaformacao)
	{
		return new Object[] { areaformacao.getId(), areaformacao.getNome() };
	}

	public void adc(AreaFormacao o) throws Exception
	{
		// list.add(o);
		list = new AreaFormacaoDAO().listar("");
		preencherTabela();
	}

	public void edt(AreaFormacao o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new AreaFormacaoDAO().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new AreaFormacaoView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new AreaFormacaoDAO().remover(list.get(i));
			list = new AreaFormacaoDAO().listar("");
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
			new AreaFormacaoView(this, list.get(i));
		}
		catch (Exception e)
		{
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new AreaFormacaoDAO().listar(x);
		preencherTabela();
	}
}
