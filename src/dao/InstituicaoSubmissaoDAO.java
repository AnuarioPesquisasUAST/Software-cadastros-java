package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.InstituicaoSubmissao;

public class InstituicaoSubmissaoDAO
{
	public InstituicaoSubmissaoDAO() {}

	public long inserir(InstituicaoSubmissao instituicaosubmissao) throws Exception
	{
		String sql = "INSERT INTO instituicaosubmissao(nome) VALUES(?)";
		long idGerado = 0;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, instituicaosubmissao.getNome());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next())
			{
				idGerado = rs.getLong(1);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		return idGerado;
	}

	public void remover(InstituicaoSubmissao instituicaosubmissao) throws Exception
	{
		String sql = "DELETE FROM instituicaosubmissao WHERE id = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, instituicaosubmissao.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(InstituicaoSubmissao instituicaosubmissao) throws Exception
	{
		String sql = "UPDATE instituicaosubmissao SET nome = ? WHERE id = ?";

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, instituicaosubmissao.getNome());
			stmt.setLong(2, instituicaosubmissao.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<InstituicaoSubmissao> listar(String condicao) throws Exception
	{
		String sql = "SELECT * FROM instituicaosubmissao " + condicao;
		ArrayList<InstituicaoSubmissao> listaInstSubmissao = new ArrayList<InstituicaoSubmissao>();
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
			{
				InstituicaoSubmissao instituicaosubmissao = new InstituicaoSubmissao();
				instituicaosubmissao.setId(rs.getLong("id"));
				instituicaosubmissao.setNome(rs.getString("nome"));
				
				listaInstSubmissao.add(instituicaosubmissao);
			}
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		return listaInstSubmissao;
	}
}
