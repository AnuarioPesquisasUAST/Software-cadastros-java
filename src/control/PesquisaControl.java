package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.AreaConhecimento;
import modelo.FonteFinanciamento;
import modelo.InstituicaoSubmissao;
import modelo.Pesquisa;
import modelo.Pesquisador;
import view.PesquisaView;
import auxiliares.Autocompletee;
import dao.PesquisaDAO;

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
						"Os dados foram inseridos com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				if (tabela != null)
					tabela.adc(objt);
				else
					autocp.adicionar(objt);
			}
			else
			{
				new PesquisaDAO().atualizar(objt);
				JOptionPane.showMessageDialog(null,
						"Os dados foram atualizados com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
							"Alerta", JOptionPane.WARNING_MESSAGE);
		}
	}

	public Pesquisa classeView()
	{
		Pesquisa x = new Pesquisa();
		x.setId(view.id.getValor());
		x.setTitulo(view.titulo.getText());
		x.setOrientador((Pesquisador) view.orientador.getSelected());
		x.setPesquisadorResponsavel((Pesquisador) view.pesquisador_responsavel
				.getSelected());
		x.setColaboradores(view.colaboradores.getList());
		x.setAnoSubmissao(view.ano_submissao.getValor());
		x.setTempoDuracao(view.tempo_duracao.getValor());
		x.setTipo(view.tipo.getText());
		x.setQualificacao(view.qualificacao.getText());
		x.setImpactoPesquisa(view.impacto_pesquisa.getText());
		x.setGerouPatente(view.gerou_patente.isSelected());
		x.setStatus(view.status.getText());
		x.setResultado(view.resultado.getText());
		x.setInstituicaoSubmissao((InstituicaoSubmissao) view.instituicao_submissao
				.getSelected());
		x.setFonteFinanciamento((FonteFinanciamento) view.fonte_financiamento
				.getSelected());
		x.setAreaConhecimentoCNPq((AreaConhecimento) view.area_conhecimento_CNPq
				.getSelected());
		x.setPalavrasChave(view.palavras_chave.getList());
		x.setInstituicoesCooperadoras(view.instituicoes_cooperadoras.getList());
		x.setLocais(view.locais.getList());
		x.setResumo(view.resumo.getText());
		return x;
	}

	public Object[] formatoTabela(Pesquisa pesquisa)
	{
		return new Object[] {

		pesquisa.getId(), pesquisa.getTitulo(),
				pesquisa.getOrientador().getId(),
				pesquisa.getPesquisadorResponsavel().getId(),
				pesquisa.getColaboradores(), pesquisa.getAnoSubmissao(),
				pesquisa.getTempoDuracao(), pesquisa.getTipo(),
				pesquisa.getQualificacao(), pesquisa.getImpactoPesquisa(),
				(pesquisa.isGerouPatente() == true ? "SIM" : "NÃO"),
				pesquisa.getStatus(), pesquisa.getResultado(),
				pesquisa.getInstituicaoSubmissao().getId(),
				pesquisa.getFonteFinanciamento().getId(),
				pesquisa.getAreaConhecimentoCNPq().getId(),
				pesquisa.getPalavrasChave(),
				pesquisa.getInstituicoesCooperadoras(),
				pesquisa.getLocais(),
                pesquisa.getResumo() };
	}
}
