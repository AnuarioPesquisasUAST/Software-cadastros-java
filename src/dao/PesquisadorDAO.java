package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.AreaFormacao;
import modelo.Curso;
import modelo.Pesquisador;

public class PesquisadorDAO
{


	public PesquisadorDAO() {}

	public long inserir(Pesquisador pesquisador) throws Exception
	{
		String sql = "INSERT INTO pesquisador(nome,nome_cientifico,email,sexo,classe,titulacao,curso_vinculado,areaformacao) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		long idGerado = 0;
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, pesquisador.getNome());
			stmt.setString(2, pesquisador.getNome_cientifico());
			stmt.setString(3, pesquisador.getEmail());
			stmt.setString(4, pesquisador.getSexo());
			stmt.setString(5, pesquisador.getClasse());
			stmt.setString(6, pesquisador.getTitulacao());
			stmt.setLong(7, pesquisador.getCurso_vinculado().getId());
			stmt.setLong(8, pesquisador.getAreaformacao().getId());
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

	public void remover(Pesquisador pesquisador) throws Exception
	{
		String sql = "DELETE FROM pesquisador WHERE id = ?";
	 
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, pesquisador.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(Pesquisador pesquisador) throws Exception
	{
		String sql = "UPDATE pesquisador SET  nome=?, nome_cientifico=?, email=?, sexo=?, classe=?, titulacao=?, curso_vinculado=?, areaformacao=? WHERE id=?";

		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, pesquisador.getNome());
			stmt.setString(2, pesquisador.getNome_cientifico());
			stmt.setString(3, pesquisador.getEmail());
			stmt.setString(4, pesquisador.getSexo());
			stmt.setString(5, pesquisador.getClasse());
			stmt.setString(6, pesquisador.getTitulacao());
			stmt.setLong(7, pesquisador.getCurso_vinculado().getId());
			stmt.setLong(8, pesquisador.getAreaformacao().getId());
			stmt.setLong(9, pesquisador.getId());
			stmt.executeUpdate(sql.toString());
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<Pesquisador> listar(String condicao) throws Exception
	{
		String sql = "SELECT * FROM pesquisador " + condicao;
		ArrayList<Pesquisador> listaPesquisador = new ArrayList<Pesquisador>();
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
			{
				Pesquisador pesquisador = new Pesquisador();
				pesquisador.setId(rs.getLong("id"));
				pesquisador.setNome(rs.getString("nome"));
				pesquisador.setNome_cientifico(rs.getString("nome_cientifico"));
				pesquisador.setEmail(rs.getString("email"));
				pesquisador.setSexo(rs.getString("sexo"));
				pesquisador.setClasse(rs.getString("classe"));
				pesquisador.setTitulacao(rs.getString("titulacao"));
				pesquisador.setCurso_vinculado(new Curso(rs.getLong("curso_vinculado")));
				pesquisador.setAreaformacao(new AreaFormacao(rs.getLong("areaformacao")));
				listaPesquisador.add(pesquisador);
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		return listaPesquisador;
	}
}
