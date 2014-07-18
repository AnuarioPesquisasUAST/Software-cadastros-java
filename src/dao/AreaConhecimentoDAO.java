package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.AreaConhecimento;

public class AreaConhecimentoDAO
{
	public AreaConhecimentoDAO() {}

	public long inserir(AreaConhecimento areaconhecimento) throws Exception
	{
		String sql = "INSERT INTO areaconhecimento (nome, ciencia, areaAvaliacao) VALUES (?, ?, ?)";
		long IdGerado = 0;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, areaconhecimento.getNome());
			stmt.setString(2, areaconhecimento.getCiencia());
			stmt.setString(3, areaconhecimento.getAreaAvaliacao());
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

	public void remover(AreaConhecimento areaconhecimento) throws Exception
	{
		String sql = "DELETE FROM areaconhecimento WHERE id = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, areaconhecimento.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(AreaConhecimento areaconhecimento) throws Exception
	{
		
		String sql = "UPDATE areaconhecimento SET nome = ?, ciencia = ?, areaAvaliacao = ? WHERE id = ?";

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, areaconhecimento.getNome());
			stmt.setString(2, areaconhecimento.getCiencia());
			stmt.setString(3, areaconhecimento.getAreaAvaliacao());
			stmt.setLong(4, areaconhecimento.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<AreaConhecimento> listar(String condicao) throws Exception
	{
		ArrayList<AreaConhecimento> listaAreaConhecimento = new ArrayList<AreaConhecimento>();
		String sql = "SELECT * FROM areaconhecimento " + condicao;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
					
			while (rs.next())
			{
				AreaConhecimento areaconhecimento = new AreaConhecimento();
				areaconhecimento.setId(rs.getLong("id"));
				areaconhecimento.setNome(rs.getString("nome"));
				areaconhecimento.setCiencia(rs.getString("ciencia"));
				areaconhecimento.setAreaAvaliacao(rs.getString("areaAvaliacao"));
				
				listaAreaConhecimento.add(areaconhecimento);
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		return listaAreaConhecimento;
	}
}
