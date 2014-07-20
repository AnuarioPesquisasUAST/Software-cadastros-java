package control;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.Pesquisa;
import view.PesquisaView;
import auxiliares.Control;
import dao.PesquisaDAO;

public class PesquisaTabela extends Control
{
	ArrayList<Pesquisa> list = new ArrayList<Pesquisa>();

	public PesquisaTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(), "Pesquisas"));
		list = new PesquisaDAO().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("id");
		colunas.add("titulo");
		colunas.add("orientador");
		colunas.add("pesquisadorResponsavel");
		colunas.add("colaboradores");
		colunas.add("anoSubmissao");
		colunas.add("tempoDuracao");
		colunas.add("tipo");
		colunas.add("qualificacao");
		colunas.add("impactoPesquisa");
		colunas.add("gerouPatente");
		colunas.add("status");
		colunas.add("resultado");
		colunas.add("instituicaoSubmissao");
		colunas.add("fonteFinanciamento");
		colunas.add("areaConhecimentoCNPq");
		colunas.add("palavrasChave");
		colunas.add("instituicoesCooperadoras");
		colunas.add("locais");
		colunas.add("resumo");
		Object linhas[][] = new Object[list.size()][];
		int i = 0;
		for (Pesquisa pesquisa : list)
		{
			linhas[i] = formatoTabela(pesquisa);
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

	public Object[] formatoTabela(Pesquisa pesquisa)
	{
		return new Object[] { pesquisa.getId(), pesquisa.getTitulo(),
				pesquisa.getOrientador(),
				pesquisa.getPesquisadorResponsavel(),
				pesquisa.getColaboradores(), pesquisa.getAnoSubmissao(),
				pesquisa.getTempoDuracao(), pesquisa.getTipo(),
				pesquisa.getQualificacao(), pesquisa.getImpactoPesquisa(),
				(pesquisa.isGerouPatente() == true ? "SIM" : "NÃO"),
				pesquisa.getStatus(), pesquisa.getResultado(),
				pesquisa.getInstituicaoSubmissao(),
				pesquisa.getFonteFinanciamento(),
				pesquisa.getAreaConhecimentoCNPq(),
				pesquisa.getPalavrasChave(),
				pesquisa.getInstituicoesCooperadoras(),
                pesquisa.getLocais(),
				pesquisa.getResumo() };
	}

	public void adc(Pesquisa o) throws Exception
	{
		// list.add(o);
		list = new PesquisaDAO().listar("");
		preencherTabela();
	}

	public void edt(Pesquisa o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new PesquisaDAO().listar("");
		preencherTabela();
		preencherTabela();
	}

	public synchronized void adicionar()
	{
		try
		{
			new PesquisaView(this);
		}
		catch (Exception e)
		{
		}
	}

	public synchronized boolean remover(int i)
	{
		try
		{
			new PesquisaDAO().remover(list.get(i));
			list = new PesquisaDAO().listar("");
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
			new PesquisaView(this, list.get(i));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new PesquisaDAO().listar(x);
		preencherTabela();
	}
}
