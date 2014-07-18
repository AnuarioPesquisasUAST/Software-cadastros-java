package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.AreaFormacao;

public class AreaFormacaoDAO
{

	public AreaFormacaoDAO() {}

	public long inserir(AreaFormacao areaformacao) throws Exception
	{
		String sql = "INSERT INTO areaformacao (nome) VALUES (?)";
		long IdGerado = 0;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, areaformacao.getNome());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next())
			{
				IdGerado = rs.getLong(1);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		return IdGerado;
	}

	public void remover(AreaFormacao areaformacao) throws Exception
	{
		String sql = "DELETE FROM areaformacao WHERE id = ?";
	
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, areaformacao.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(AreaFormacao areaformacao) throws Exception
	{
		String sql = "UPDATE areaformacao SET nome = ? WHERE id = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, areaformacao.getNome());
			stmt.setLong(2, areaformacao.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<AreaFormacao> listar(String condicao) throws Exception
	{
		ArrayList<AreaFormacao> listaAreaFormacao = new ArrayList<AreaFormacao>();
		String sql = "SELECT * FROM areaformacao " + condicao;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
			{
				AreaFormacao areaformacao = new AreaFormacao();
				areaformacao.setId(rs.getLong("id"));
				areaformacao.setNome(rs.getString("nome"));
				listaAreaFormacao.add(areaformacao);
			}
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		return listaAreaFormacao;
	}
}
