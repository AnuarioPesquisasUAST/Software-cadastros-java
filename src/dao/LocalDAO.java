package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Local;

public class LocalDAO
{
	public LocalDAO() {}

	public long inserir(Local local) throws Exception
	{
		String sql = "INSERT INTO local(cidade, estado, descricao) VALUES (?, ?, ?)";
		long idGerado = 0;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, local.getCidade());
			stmt.setString(2, local.getEstado());
			stmt.setString(3, local.getDescricao());
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

	public void remover(Local local) throws Exception
	{
		String sql = "DELETE FROM local WHERE id = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, local.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(Local local) throws Exception
	{
		String sql = "UPDATE local SET cidade = ?, estado = ?, descricao = ? WHERE id = ?";

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, local.getCidade());
			stmt.setString(2, local.getEstado());
			stmt.setString(3, local.getDescricao());
			stmt.setLong(4, local.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<Local> listar(String condicao) throws Exception
	{
		String sql = "SELECT * FROM local " + condicao;
		ArrayList<Local> listaLocal = new ArrayList<Local>();
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
			{
				Local local = new Local();
				local.setId(rs.getLong("id"));
				local.setCidade(rs.getString("cidade"));
				local.setEstado(rs.getString("estado"));
				local.setDescricao(rs.getString("descricao"));
				
				listaLocal.add(local);
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		return listaLocal;
	}
}
