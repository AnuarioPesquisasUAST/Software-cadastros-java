package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Curso;

public class CursoDAO
{
	public CursoDAO() {}

	public long inserir(Curso curso) throws Exception
	{
		String sql = "INSERT INTO curso(nome) VALUES (?)";
		
		long idGerado = 0;
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, curso.getNome());
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

	public void remover(Curso curso) throws Exception
	{
		String sql = "DELETE FROM curso WHERE id = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, curso.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(Curso curso) throws Exception
	{
		String sql = "UPDATE curso SET nome = ? WHERE id = ?";

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, curso.getNome());
			stmt.setLong(2, curso.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<Curso> listar(String condicao) throws Exception
	{
		ArrayList<Curso> listaCurso = new ArrayList<Curso>();
		String sql = "SELECT * FROM curso " + condicao;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
			{
				Curso curso = new Curso();
				curso.setId(rs.getLong("id"));
				curso.setNome(rs.getString("nome"));
				listaCurso.add(curso);
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return listaCurso;
	}
}
