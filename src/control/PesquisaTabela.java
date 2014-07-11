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

public class PesquisaTabela extends Control
{
	ArrayList<Pesquisa> list = new ArrayList<>();

	public PesquisaTabela() throws Exception
	{
		view.setBorder(new TitledBorder(new EtchedBorder(), "Pesquisas"));
		list = new PesquisaMysql().listar("");
		preencherTabela();
	}

	public void preencherTabela() throws Exception
	{
		ArrayList<String> colunas = new ArrayList<>();
		colunas.add("id");
		colunas.add("titulo");
		colunas.add("orientador");
		colunas.add("pesquisador_responsavel");
		colunas.add("colaboradores");
		colunas.add("ano_submissao");
		colunas.add("tempo_duracao");
		colunas.add("tipo");
		colunas.add("qualificacao");
		colunas.add("impacto_pesquisa");
		colunas.add("gerou_patente");
		colunas.add("status");
		colunas.add("resultado");
		colunas.add("instituicao_submissao");
		colunas.add("fonte_financiamento");
		colunas.add("area_conhecimento_CNPq");
		colunas.add("palavras_chave");
		colunas.add("instituicoes_cooperadoras");
		colunas.add("local");
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
				pesquisa.getPesquisador_responsavel(),
				pesquisa.getColaboradores(), pesquisa.getAno_submissao(),
				pesquisa.getTempo_duracao(), pesquisa.getTipo(),
				pesquisa.getQualificacao(), pesquisa.getImpacto_pesquisa(),
				(pesquisa.isGerou_patente() == true ? "SIM" : "NÃO"),
				pesquisa.getStatus(), pesquisa.getResultado(),
				pesquisa.getInstituicao_submissao(),
				pesquisa.getFonte_financiamento(),
				pesquisa.getArea_conhecimento_CNPq(),
				pesquisa.getPalavras_chave(),
				pesquisa.getInstituicoes_cooperadoras(),
				pesquisa.getLocal(), pesquisa.getResumo() };
	}

	public void adc(Pesquisa o) throws Exception
	{
		// list.add(o);
		list = new PesquisaMysql().listar("");
		preencherTabela();
	}

	public void edt(Pesquisa o) throws Exception
	{
		int i = view.getTabela().convertRowIndexToModel(
				view.getTabela().getSelectedRow());
		// list.set(i,o);
		list = new PesquisaMysql().listar("");
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
			new PesquisaMysql().remover(list.get(i));
			list = new PesquisaMysql().listar("");
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
		}
	}

	public void pesquisar(String x) throws Exception
	{
		list = new PesquisaMysql().listar(x);
		preencherTabela();
	}
}
