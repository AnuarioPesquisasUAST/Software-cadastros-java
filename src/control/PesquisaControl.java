package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import auxiliares.Autocompletee;
import dao.*;
import view.*;
import modelo.*;
import modelo.Pesquisa;

public class PesquisaControl implements ActionListener
{
	PesquisaView view;
	PesquisaTabela tabela;
	Autocompletee autocp;
	Pesquisa obj;

	public PesquisaControl(PesquisaView v, PesquisaTabela t) throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public PesquisaControl(PesquisaView v, Autocompletee t) throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public PesquisaControl(PesquisaView v, PesquisaTabela t, Pesquisa o)
			throws Exception
	{
		this.view = v;
		this.tabela = t;
		this.obj = o;
	}

	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			Pesquisa objt = classeView();
			if (obj == null)
			{
				long x = new PesquisaDAO().inserir(objt);
				objt.setId(x);
				JOptionPane.showMessageDialog(null,
						"Os dados foram inseridos com sucesso", "Sucesso", 0);
				if (tabela != null)
					tabela.adc(objt);
				else
					autocp.adicionar(objt);
			}
			else
			{
				new PesquisaDAO().atualizar(objt);
				JOptionPane.showMessageDialog(null,
						"Os dados foram inseridos com sucesso", "Sucesso", 1);
				tabela.edt(objt);
			}
			view.dispose();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(
							null,
							"Verifique se os campos estão preenchidos corretamente ou se estão repetidos",
							"Erro", 0);
		}
	}

	public Pesquisa classeView()
	{
		Pesquisa x = new Pesquisa();
		x.setId(view.id.getValor());
		x.setTitulo(view.titulo.getText());
		x.setOrientador((Pesquisador) view.orientador.getSelected());
		x.setPesquisador_responsavel((Pesquisador) view.pesquisador_responsavel
				.getSelected());
		x.setColaboradores(view.colaboradores.getList());
		x.setAno_submissao(view.ano_submissao.getValor());
		x.setTempo_duracao(view.tempo_duracao.getValor());
		x.setTipo(view.tipo.getText());
		x.setQualificacao(view.qualificacao.getText());
		x.setImpacto_pesquisa(view.impacto_pesquisa.getText());
		x.setGerou_patente(view.gerou_patente.isSelected());
		x.setStatus(view.status.getText());
		x.setResultado(view.resultado.getText());
		x.setInstituicao_submissao((InstituicaoSubmissao) view.instituicao_submissao
				.getSelected());
		x.setFonte_financiamento((FonteFinanciamento) view.fonte_financiamento
				.getSelected());
		x.setArea_conhecimento_CNPq((AreaConhecimento) view.area_conhecimento_CNPq
				.getSelected());
		x.setPalavras_chave(view.palavras_chave.getList());
		x.setInstituicoes_cooperadoras(view.instituicoes_cooperadoras.getList());
		x.setLocais(view.locais.getList());
		x.setResumo(view.resumo.getText());
		return x;
	}

	public Object[] formatoTabela(Pesquisa pesquisa)
	{
		return new Object[] {

		pesquisa.getId(), pesquisa.getTitulo(),
				pesquisa.getOrientador().getId(),
				pesquisa.getPesquisador_responsavel().getId(),
				pesquisa.getColaboradores(), pesquisa.getAno_submissao(),
				pesquisa.getTempo_duracao(), pesquisa.getTipo(),
				pesquisa.getQualificacao(), pesquisa.getImpacto_pesquisa(),
				(pesquisa.isGerou_patente() == true ? "SIM" : "NÃO"),
				pesquisa.getStatus(), pesquisa.getResultado(),
				pesquisa.getInstituicao_submissao().getId(),
				pesquisa.getFonte_financiamento().getId(),
				pesquisa.getArea_conhecimento_CNPq().getId(),
				pesquisa.getPalavras_chave(),
				pesquisa.getInstituicoes_cooperadoras(),
				pesquisa.getLocais(),
                pesquisa.getResumo() };
	}
}
