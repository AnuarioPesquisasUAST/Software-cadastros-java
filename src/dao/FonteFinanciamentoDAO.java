package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.FonteFinanciamento;

public class FonteFinanciamentoDAO
{
	
	public FonteFinanciamentoDAO() {}

	public long inserir(FonteFinanciamento fonteFinanciamento) throws Exception
	{
		String sql = "INSERT INTO fontefinanciamento(nome) VALUES (?)";
		long idGeradox = 0;
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, fonteFinanciamento.getNome());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next())
			{
				idGeradox = rs.getLong(1);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		return idGeradox;
	}

	public void remover(FonteFinanciamento fontefinanciamento) throws Exception
	{
		String sql = "DELETE FROM fontefinanciamento WHERE id = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, fontefinanciamento.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(FonteFinanciamento fontefinanciamento) throws Exception
	{
		
		String sql = "UPDATE fontefinanciamento SET nome = ? WHERE id = ?" ;

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, fontefinanciamento.getNome());
			stmt.setLong(2, fontefinanciamento.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<FonteFinanciamento> listar(String condicao) throws Exception
	{
		String sql = "SELECT * FROM fontefinanciamento " + condicao;
		ArrayList<FonteFinanciamento> listaFonteFinanciamento = new ArrayList<FonteFinanciamento>();
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
					
			while (rs.next())
			{
				FonteFinanciamento fontefinanciamento = new FonteFinanciamento();
				fontefinanciamento.setId(rs.getLong("id"));
				fontefinanciamento.setNome(rs.getString("nome"));
				
				listaFonteFinanciamento.add(fontefinanciamento);
			}
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return listaFonteFinanciamento;
	}
}
