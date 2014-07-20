package modelo;

import java.util.ArrayList;

public class Pesquisa
{
	long id;
	String titulo;
	Pesquisador orientador;
	Pesquisador pesquisadorResponsavel;
	ArrayList<Pesquisador> colaboradores;
	int anoSubmissao;
	int tempoDuracao;
	String tipo;
	String qualificacao;
	String impactoPesquisa;
	boolean gerouPatente;
	String status;
	String resultado;
	InstituicaoSubmissao instituicaoSubmissao;
	FonteFinanciamento fonteFinanciamento;
	AreaConhecimento areaConhecimentoCNPq;
	ArrayList<PalavraChave> palavrasChave;
	ArrayList<InstituicaoCooperadora> instituicoesCooperadoras;
	ArrayList<Local> locais;
	StringBuffer resumo;

	public Pesquisa() {}

	public Pesquisa(long idPesquisa)
	{
		id = idPesquisa;
	}

	public String toString()
	{
		if (titulo != null)
			return titulo.toString();
		return id + "";
	}

	public long getId()
	{
		return id;
	}

	public void setId(long idPesquisa)
	{
		this.id = idPesquisa;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public Pesquisador getOrientador()
	{
		return orientador;
	}

	public void setOrientador(Pesquisador pesquisadorOrientador)
	{
		this.orientador = pesquisadorOrientador;
	}

	public Pesquisador getPesquisadorResponsavel()
	{
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pesquisador pesquisadorResponsavel)
	{
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public ArrayList<Pesquisador> getColaboradores()
	{
		return colaboradores;
	}

	public void setColaboradores(ArrayList<Pesquisador> colaboradores)
	{
		this.colaboradores = colaboradores;
	}

	public int getAnoSubmissao()
	{
		return anoSubmissao;
	}

	public void setAnoSubmissao(int anoSubmissao)
	{
		this.anoSubmissao = anoSubmissao;
	}

	public int getTempoDuracao()
	{
		return tempoDuracao;
	}

	public void setTempoDuracao(int tempoDuracao)
	{
		this.tempoDuracao = tempoDuracao;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public String getQualificacao()
	{
		return qualificacao;
	}

	public void setQualificacao(String qualificacao)
	{
		this.qualificacao = qualificacao;
	}

	public String getImpactoPesquisa()
	{
		return impactoPesquisa;
	}

	public void setImpactoPesquisa(String impactoPesquisa)
	{
		this.impactoPesquisa = impactoPesquisa;
	}

	public boolean isGerouPatente()
	{
		return gerouPatente;
	}

	public void setGerouPatente(boolean gerouPatente)
	{
		this.gerouPatente = gerouPatente;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getResultado()
	{
		return resultado;
	}

	public void setResultado(String resultado)
	{
		this.resultado = resultado;
	}

	public InstituicaoSubmissao getInstituicaoSubmissao()
	{
		return instituicaoSubmissao;
	}

	public void setInstituicaoSubmissao(InstituicaoSubmissao instSubmissao)
	{
		this.instituicaoSubmissao = instSubmissao;
	}

	public FonteFinanciamento getFonteFinanciamento()
	{
		return fonteFinanciamento;
	}

	public void setFonteFinanciamento(FonteFinanciamento fonteFinan)
	{
		this.fonteFinanciamento = fonteFinan;
	}

	public AreaConhecimento getAreaConhecimentoCNPq()
	{
		return areaConhecimentoCNPq;
	}

	public void setAreaConhecimentoCNPq(AreaConhecimento areaConhecimento)
	{
		this.areaConhecimentoCNPq = areaConhecimento;
	}

	public ArrayList<PalavraChave> getPalavrasChave()
	{
		return palavrasChave;
	}

	public void setPalavrasChave(ArrayList<PalavraChave> palavrasChave)
	{
		this.palavrasChave = palavrasChave;
	}

	public ArrayList<InstituicaoCooperadora> getInstituicoesCooperadoras()
	{
		return instituicoesCooperadoras;
	}

	public void setInstituicoesCooperadoras(ArrayList<InstituicaoCooperadora> instCoop)
	{
		this.instituicoesCooperadoras = instCoop;
	}

	public ArrayList<Local> getLocais()
	{
		return this.locais;
	}

	public void setLocais(ArrayList<Local> locais)
	{
		this.locais = locais;
	}

	public StringBuffer getResumo()
	{
		return resumo;
	}

	public void setResumo(StringBuffer resumo)
	{
		this.resumo = resumo;
	}

	public void setResumo(String x)
	{
		this.resumo = new StringBuffer(x);
	}

}