package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Pesquisa;

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
			stmt.setLong(3, pesquisa.getPesquisadorResponsavel().getId());
			stmt.setInt(4, pesquisa.getAnoSubmissao());
			stmt.setInt(5, pesquisa.getTempoDuracao());
			stmt.setString(6, pesquisa.getTipo());
			stmt.setString(7,pesquisa.getQualificacao());
			stmt.setString(8, pesquisa.getImpactoPesquisa());
			stmt.setBoolean(9, pesquisa.isGerouPatente());
			stmt.setString(10, pesquisa.getStatus());
			stmt.setString(11, pesquisa.getResultado());
			stmt.setLong(12, pesquisa.getInstituicaoSubmissao().getId());
			stmt.setLong(13, pesquisa.getFonteFinanciamento().getId());
			stmt.setLong(14, pesquisa.getAreaConhecimentoCNPq().getId());
			stmt.setString(15, pesquisa.getResumo().toString());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next())
			{
				idGerado = rs.getLong(1);
			}

			new PesquisacolaboradoresDAO().inserir(idGerado, pesquisa.getColaboradores());
			new PesquisaPalavrasChaveDAO().inserir(idGerado, pesquisa.getPalavrasChave());
			new PesquisaInstituicoesCooperadorasDAO().inserir(idGerado, pesquisa.getInstituicoesCooperadoras());
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
		String sql = "DELETE FROM pesquisa WHERE id = ?";
		
		new PesquisacolaboradoresDAO().remover(pesquisa.getId());
		new PesquisaPalavrasChaveDAO().remover(pesquisa.getId());
		new PesquisaInstituicoesCooperadorasDAO().remover(pesquisa.getId());
		new PesquisaLocalDAO().remover(pesquisa.getId());
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, pesquisa.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(Pesquisa pesquisa) throws Exception
	{
		String sql = "UPDATE pesquisa SET " +
				"titulo = ?," +
				"orientador = ?," +
				"pesquisador_responsavel = ?," +
				"ano_submissao = ?," +
				"tempo_duracao = ?," +
				"tipo = ?," +
				"qualificacao = ?," +
				"impacto_pesquisa = ?," +
				"gerou_patente = ?," +
				"status = ?," +
				"resultado = ?," +
				"instituicao_submissao = ?," +
				"fonte_financiamento = ?," +
				"area_conhecimento_CNPq = ?," +
				"resumo = ?" +
				" WHERE id = ?";
		
		new PesquisacolaboradoresDAO().remover(pesquisa.getId());
		new PesquisacolaboradoresDAO().inserir(pesquisa.getId(), pesquisa.getColaboradores());
		new PesquisaPalavrasChaveDAO().remover(pesquisa.getId());
		new PesquisaPalavrasChaveDAO().inserir(pesquisa.getId(), pesquisa.getPalavrasChave());
		new PesquisaInstituicoesCooperadorasDAO().remover(pesquisa.getId());
		new PesquisaInstituicoesCooperadorasDAO().inserir(pesquisa.getId(),	pesquisa.getInstituicoesCooperadoras());
		new PesquisaLocalDAO().remover(pesquisa.getId());
		new PesquisaLocalDAO().inserir(pesquisa.getId(), pesquisa.getLocais());

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, pesquisa.getTitulo());
			stmt.setLong(2, pesquisa.getOrientador().getId());
			stmt.setLong(3, pesquisa.getPesquisadorResponsavel().getId());
			stmt.setInt(4, pesquisa.getAnoSubmissao());
			stmt.setInt(5, pesquisa.getTempoDuracao());
			stmt.setString(6, pesquisa.getTipo());
			stmt.setString(7,pesquisa.getQualificacao());
			stmt.setString(8, pesquisa.getImpactoPesquisa());
			stmt.setBoolean(9, pesquisa.isGerouPatente());
			stmt.setString(10, pesquisa.getStatus());
			stmt.setString(11, pesquisa.getResultado());
			stmt.setLong(12, pesquisa.getInstituicaoSubmissao().getId());
			stmt.setLong(13, pesquisa.getFonteFinanciamento().getId());
			stmt.setLong(14, pesquisa.getAreaConhecimentoCNPq().getId());
			stmt.setString(15, pesquisa.getResumo().toString());
			stmt.setLong(16, pesquisa.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<Pesquisa> listar(String condicao) throws Exception
	{
		String sql = "SELECT * FROM pesquisa " + condicao;
		ArrayList<Pesquisa> listaPesquisa = new ArrayList<Pesquisa>();
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
			{
				Pesquisa pesquisa = new Pesquisa();
				pesquisa.setId(rs.getLong("id"));
				pesquisa.setTitulo(rs.getString("titulo"));
				pesquisa.setOrientador(new PesquisadorDAO().listar(" WHERE id =" + rs.getLong("orientador")).get(0));
				pesquisa.setPesquisadorResponsavel(new PesquisadorDAO().listar(" WHERE id =" + rs.getLong("pesquisador_responsavel")).get(0));
				pesquisa.setColaboradores(new PesquisacolaboradoresDAO().listar(rs.getLong("id")));
				pesquisa.setAnoSubmissao(rs.getInt("ano_submissao"));
				pesquisa.setTempoDuracao(rs.getInt("tempo_duracao"));
				pesquisa.setTipo(rs.getString("tipo"));
				pesquisa.setQualificacao(rs.getString("qualificacao"));
				pesquisa.setImpactoPesquisa(rs.getString("impacto_pesquisa"));
				pesquisa.setGerouPatente(rs.getBoolean("gerou_patente"));
				pesquisa.setStatus(rs.getString("status"));
				pesquisa.setResultado(rs.getString("resultado"));
				pesquisa.setInstituicaoSubmissao(new InstituicaoSubmissaoDAO().listar(" WHERE id = " + rs.getLong("instituicao_submissao")).get(0));
				pesquisa.setFonteFinanciamento(new FonteFinanciamentoDAO().listar(" WHERE id = " + rs.getLong("fonte_financiamento")).get(0));
				pesquisa.setAreaConhecimentoCNPq(new AreaConhecimentoDAO().listar(" WHERE id = "+ rs.getLong("area_conhecimento_CNPq")).get(0));
				pesquisa.setPalavrasChave(new PesquisaPalavrasChaveDAO().listar(rs.getLong("id")));
				pesquisa.setInstituicoesCooperadoras(new PesquisaInstituicoesCooperadorasDAO().listar(rs.getLong("id")));
				pesquisa.setLocais(new PesquisaLocalDAO().listar(rs.getLong("id")));
				pesquisa.setResumo((String) rs.getObject("resumo"));
				listaPesquisa.add(pesquisa);
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return listaPesquisa;
	}
}
