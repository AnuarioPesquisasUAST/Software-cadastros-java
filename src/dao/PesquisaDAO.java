package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Pesquisa;

import modelo.*;

public class PesquisaDAO
{

	public PesquisaDAO()
	{
	}

	public long inserir(Pesquisa pesquisa) throws Exception
	{
		String sql = "INSERT INTO pesquisa (titulo, orientador, pesquisador_responsavel, ano_submissao, tempo_duracao, tipo, qualificacao, impacto_pesquisa, gerou_patente, status, resultado, instituicao_submissao, fonte_financiamento, area_conhecimento_CNPq, resumo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		long idGerado = 0;

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, pesquisa.getTitulo());
			stmt.setLong(2, pesquisa.getOrientador().getId());
			stmt.setLong(3, pesquisa.getPesquisador_responsavel().getId());
			stmt.setInt(4, pesquisa.getAno_submissao());
			stmt.setInt(5, pesquisa.getTempo_duracao());
			stmt.setString(6, pesquisa.getTipo());
			stmt.setString(7,pesquisa.getQualificacao());
			stmt.setString(8, pesquisa.getImpacto_pesquisa());
			stmt.setBoolean(9, pesquisa.isGerou_patente());
			stmt.setString(10, pesquisa.getStatus());
			stmt.setString(11, pesquisa.getResultado());
			stmt.setLong(12, pesquisa.getInstituicao_submissao().getId());
			stmt.setLong(13, pesquisa.getFonte_financiamento().getId());
			stmt.setLong(14, pesquisa.getArea_conhecimento_CNPq().getId());
			stmt.setNString(15, pesquisa.getResumo().toString());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next())
			{
				idGerado = rs.getLong(1);
			}

			new PesquisacolaboradoresDAO().inserir(idGerado, pesquisa.getColaboradores());
			new PesquisaPalavrasChaveDAO().inserir(idGerado, pesquisa.getPalavras_chave());
			new PesquisaInstituicoesCooperadorasDAO().inserir(idGerado, pesquisa.getInstituicoes_cooperadoras());
			new PesquisaLocalDAO().inserir(idGerado, pesquisa.getLocais());
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		return idGerado;
	}

	public void remover(Pesquisa pesquisa) throws Exception
	{
		String sql = null;
		long x = pesquisa.getId();
		new PesquisacolaboradoresDAO().remover(x);
		new PesquisaPalavrasChaveDAO().remover(x);
		new PesquisaInstituicoesCooperadorasDAO().remover(x);
		new PesquisaLocalDAO().remover(x);
		sql = "DELETE FROM pesquisa WHERE id = '" + x + "'";
		try
		{
			comando.executeUpdate(sql.toString());
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(Pesquisa pesquisa) throws Exception
	{
		long x = pesquisa.getId();
		String sql = "UPDATE pesquisa SET " + "titulo = '"
				+ pesquisa.getTitulo() + "'," + "orientador = '"
				+ pesquisa.getOrientador().getId() + "',"
				+ "pesquisador_responsavel = '"
				+ pesquisa.getPesquisador_responsavel().getId() + "',"
				+ "ano_submissao = '" + pesquisa.getAno_submissao() + "',"
				+ "tempo_duracao = '" + pesquisa.getTempo_duracao() + "',"
				+ "tipo = '" + pesquisa.getTipo() + "'," + "qualificacao = '"
				+ pesquisa.getQualificacao() + "'," + "impacto_pesquisa = '"
				+ pesquisa.getImpacto_pesquisa() + "'," + "gerou_patente = '"
				+ (pesquisa.isGerou_patente() == true ? 1 : 0) + "',"
				+ "status = '" + pesquisa.getStatus() + "'," + "resultado = '"
				+ pesquisa.getResultado() + "'," + "instituicao_submissao = '"
				+ pesquisa.getInstituicao_submissao().getId() + "',"
				+ "fonte_financiamento = '"
				+ pesquisa.getFonte_financiamento().getId() + "',"
				+ "area_conhecimento_CNPq = '"
				+ pesquisa.getArea_conhecimento_CNPq().getId() + "',"
				+ "resumo = '" + pesquisa.getResumo() + "'" + " WHERE id = '"
				+ x + "'";
		new PesquisacolaboradoresDAO().remover(x);
		new PesquisacolaboradoresDAO().inserir(x, pesquisa.getColaboradores());
		new PesquisaPalavrasChaveDAO().remover(x);
		new PesquisaPalavrasChaveDAO().inserir(x, pesquisa.getPalavras_chave());
		new PesquisaInstituicoesCooperadorasDAO().remover(x);
		new PesquisaInstituicoesCooperadorasDAO().inserir(x,
				pesquisa.getInstituicoes_cooperadoras());
		new PesquisaLocalDAO().remover(x);
		new PesquisaLocalDAO().inserir(x, pesquisa.getLocais());

		try
		{
			comando.executeUpdate(sql.toString());
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<Pesquisa> listar(String condicao) throws Exception
	{
		ArrayList<Pesquisa> lista = new ArrayList<Pesquisa>();
		try
		{
			System.err.println("Condicao: " + condicao);
			ResultSet rs = comando.executeQuery("SELECT * FROM pesquisa "
					+ condicao);
			while (rs.next())
			{
				Pesquisa pesquisa = new Pesquisa();
				pesquisa.setId(rs.getLong("id"));
				// pesquisa.setId((Long)rs.getObject("id"));
				pesquisa.setTitulo(rs.getString("titulo"));
				// pesquisa.setTitulo((String)rs.getObject("titulo"));
				// pesquisa.setOrientador(new
				// Pesquisador(rs.getLong("orientador")));
				pesquisa.setOrientador(new PesquisadorDAO().listar(
						" WHERE id =" + (rs.getLong("orientador"))).get(0));
				// pesquisa.setPesquisador_responsavel(new Pesquisador(rs
				// .getLong("pesquisador_responsavel")));
				pesquisa.setPesquisador_responsavel(new PesquisadorDAO()
						.listar(" WHERE id ="
								+ (rs.getLong("pesquisador_responsavel"))).get(
								0));
				pesquisa.setColaboradores(new PesquisacolaboradoresDAO()
						.listar(rs.getLong("id")));
				pesquisa.setAno_submissao(rs.getInt("ano_submissao"));
				// pesquisa.setAno_submissao((Int)rs.getObject("ano_submissao"));
				pesquisa.setTempo_duracao(rs.getInt("tempo_duracao"));
				// pesquisa.setTempo_duracao((Int)rs.getObject("tempo_duracao"));
				pesquisa.setTipo(rs.getString("tipo"));
				// pesquisa.setTipo((String)rs.getObject("tipo"));
				pesquisa.setQualificacao(rs.getString("qualificacao"));
				// pesquisa.setQualificacao((String)rs.getObject("qualificacao"));
				pesquisa.setImpacto_pesquisa(rs.getString("impacto_pesquisa"));
				// pesquisa.setImpacto_pesquisa((String)rs.getObject("impacto_pesquisa"));
				pesquisa.setGerou_patente(rs.getBoolean("gerou_patente"));
				// pesquisa.setGerou_patente((Boolean)rs.getObject("gerou_patente"));
				pesquisa.setStatus(rs.getString("status"));
				// pesquisa.setStatus((String)rs.getObject("status"));
				pesquisa.setResultado(rs.getString("resultado"));
				// pesquisa.setResultado((String)rs.getObject("resultado"));
				// pesquisa.setInstituicao_submissao(new InstituicaoSubmissao(rs
				// .getLong("instituicao_submissao")));
				pesquisa.setInstituicao_submissao(new InstituicaoSubmissaoDAO()
						.listar(" WHERE id ="
								+ (rs.getLong("instituicao_submissao"))).get(0));
				// pesquisa.setFonte_financiamento(new FonteFinanciamento(rs
				// .getLong("fonte_financiamento")));
				pesquisa.setFonte_financiamento(new FonteFinanciamentoDAO()
						.listar(" WHERE id ="
								+ (rs.getLong("fonte_financiamento"))).get(0));
				// pesquisa.setArea_conhecimento_CNPq(new AreaConhecimento(rs
				// .getLong("area_conhecimento_CNPq")));
				pesquisa.setArea_conhecimento_CNPq(new AreaConhecimentoDAO()
						.listar(" WHERE id ="
								+ (rs.getLong("area_conhecimento_CNPq")))
						.get(0));
				pesquisa.setPalavras_chave(new PesquisaPalavrasChaveDAO()
						.listar(rs.getLong("id")));
				pesquisa.setInstituicoes_cooperadoras(new PesquisaInstituicoesCooperadorasDAO()
						.listar(rs.getLong("id")));
				// pesquisa.setLocal(new Local(rs.getLong("local")));
				pesquisa.setLocais(new PesquisaLocalDAO().listar(rs
						.getLong("id")));
				// pesquisa.setResumo(rs.getString("resumo"));
				pesquisa.setResumo((String) rs.getObject("resumo"));
				lista.add(pesquisa);
			}
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return lista;
	}
}
