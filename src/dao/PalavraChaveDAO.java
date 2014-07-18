package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.PalavraChave;

public class PalavraChaveDAO
{
	public PalavraChaveDAO() {}

	public long inserir(PalavraChave palavrachave) throws Exception
	{
		String sql = "INSERT INTO palavrachave (palavra) VALUES(?)";
		long idGerado = 0;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, palavrachave.getPalavra());
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

	public void remover(PalavraChave palavrachave) throws Exception
	{
		String sql = "DELETE FROM palavrachave WHERE id = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, palavrachave.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(PalavraChave palavrachave) throws Exception
	{
		String sql = "UPDATE palavrachave SET palavra = ? WHERE id = ?";

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, palavrachave.getPalavra());
			stmt.setLong(2, palavrachave.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<PalavraChave> listar(String condicao) throws Exception
	{
		String sql = "SELECT * FROM palavrachave " + condicao;
		ArrayList<PalavraChave> listaPalavraChave = new ArrayList<PalavraChave>();
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
			{
				PalavraChave palavrachave = new PalavraChave();
				palavrachave.setId(rs.getLong("id"));
				palavrachave.setPalavra(rs.getString("palavra"));
				
				listaPalavraChave.add(palavrachave);
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		return listaPalavraChave;
	}
}
