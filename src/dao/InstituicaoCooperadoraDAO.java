package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.InstituicaoCooperadora;

public class InstituicaoCooperadoraDAO
{
	
	public InstituicaoCooperadoraDAO(){}

	public long inserir(InstituicaoCooperadora instituicaocooperadora) throws Exception
	{
		String sql = "INSERT INTO instituicaocooperadora(nome) VALUES (?)";
		long idGerado = 0;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, instituicaocooperadora.getNome());
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

	public void remover(InstituicaoCooperadora instituicaocooperadora) throws Exception
	{
		String sql =  "DELETE FROM instituicaocooperadora WHERE id = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, instituicaocooperadora.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(InstituicaoCooperadora instituicaocooperadora) throws Exception
	{
		String sql = "UPDATE instituicaocooperadora SET nome = ? WHERE id = ?";

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, instituicaocooperadora.getNome());
			stmt.setLong(2, instituicaocooperadora.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<InstituicaoCooperadora> listar(String condicao) throws Exception
	{
		String sql = "SELECT * FROM instituicaocooperadora " + condicao;
		ArrayList<InstituicaoCooperadora> listaInstituicaoCooperadora = new ArrayList<InstituicaoCooperadora>();
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
			{
				InstituicaoCooperadora instituicaocooperadora = new InstituicaoCooperadora();
				instituicaocooperadora.setId(rs.getLong("id"));
				instituicaocooperadora.setNome(rs.getString("nome"));
				
				listaInstituicaoCooperadora.add(instituicaocooperadora);
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		return listaInstituicaoCooperadora;
	}
}
