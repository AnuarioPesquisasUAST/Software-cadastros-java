package modelo;

public class Pesquisador
{
	long id;
	String nome;
	String nomeCientifico;
	String email;
	String sexo;
	String classe;
	String titulacao;
	Curso cursoVinculado;
	AreaFormacao areaformacao;
	String curriculoLattes;

	
	public Pesquisador()
	{
	}

	public Pesquisador(long i)
	{
		id = i;
	}

	
	public long getId()
	{
		return id;
	}

	public void setId(long x)
	{
		this.id = x;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String x)
	{
		this.nome = x;
	}

	public String getNome_cientifico()
	{
		return nomeCientifico;
	}

	public void setNome_cientifico(String x)
	{
		this.nomeCientifico = x;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String x)
	{
		this.email = x;
	}

	public String getSexo()
	{
		return sexo;
	}

	public void setSexo(String x)
	{
		this.sexo = x;
	}

	public String getClasse()
	{
		return classe;
	}

	public void setClasse(String x)
	{
		this.classe = x;
	}

	public String getTitulacao()
	{
		return titulacao;
	}

	public void setTitulacao(String x)
	{
		this.titulacao = x;
	}

	public Curso getCursoVinculado()
	{
		return cursoVinculado;
	}

	public void setCursoVinculado(Curso x)
	{
		this.cursoVinculado = x;
	}

	public AreaFormacao getAreaformacao()
	{
		return areaformacao;
	}

	public void setAreaformacao(AreaFormacao x)
	{
		this.areaformacao = x;
	}
	
	public String getCurriculoLattes()
	{
		return curriculoLattes;
	}

	public void setCurriculoLattes(String curriculoLattes)
	{
		this.curriculoLattes = curriculoLattes;
	}
	
	public String toString()
	{
		if (nome != null)
			return nome.toString();
		return id + "";
	}

}