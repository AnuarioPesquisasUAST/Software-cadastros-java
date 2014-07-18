package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Pesquisador;

import modelo.*;

public class PesquisadorDAO
{
	ConnectionFactory conexao = null;
	private Statement comando;

	public PesquisadorDAO() throws Exception
	{
		conexao = ConnectionFactory.getInstance();
		try
		{
			this.comando = conexao.getConnection().createStatement();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public long inserir(Pesquisador pesquisador) throws Exception
	{
		String sql = "INSERT INTO pesquisador "
				+ "(nome,nome_cientifico,email,sexo,classe,titulacao,curso_vinculado,areaformacao) VALUES('"
				+ pesquisador.getNome() + "','"
				+ pesquisador.getNome_cientifico() + "','"
				+ pesquisador.getEmail() + "','" + pesquisador.getSexo()
				+ "','" + pesquisador.getClasse() + "','"
				+ pesquisador.getTitulacao() + "','"
				+ pesquisador.getCurso_vinculado().getId() + "','"
				+ pesquisador.getAreaformacao().getId() + "')";
		long x = 0;
		try
		{
			java.sql.PreparedStatement inserirReturnId = conexao
					.getConnection().prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
			inserirReturnId.executeUpdate();
			ResultSet rs = inserirReturnId.getGeneratedKeys();
			if (rs.next())
				x = rs.getLong(1);
		}
		catch (SQLException e)
		{
			throw e;
		}
		return x;
	}

	public void remover(Pesquisador pesquisador) throws Exception
	{
		String sql = null;
		long x = pesquisador.getId();
		sql = "DELETE FROM pesquisador WHERE id = '" + x + "'";
		try
		{
			comando.executeUpdate(sql.toString());
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void atualizar(Pesquisador pesquisador) throws Exception
	{
		long x = pesquisador.getId();
		String sql = "UPDATE pesquisador SET " + "nome = '"
				+ pesquisador.getNome() + "'," + "nome_cientifico = '"
				+ pesquisador.getNome_cientifico() + "'," + "email = '"
				+ pesquisador.getEmail() + "'," + "sexo = '"
				+ pesquisador.getSexo() + "'," + "classe = '"
				+ pesquisador.getClasse() + "'," + "titulacao = '"
				+ pesquisador.getTitulacao() + "'," + "curso_vinculado = '"
				+ pesquisador.getCurso_vinculado().getId() + "',"
				+ "areaformacao = '" + pesquisador.getAreaformacao().getId()
				+ "'" + " WHERE id = '" + x + "'";

		try
		{
			comando.executeUpdate(sql.toString());
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<Pesquisador> listar(String condicao) throws Exception
	{
		ArrayList<Pesquisador> lista = new ArrayList<Pesquisador>();
		try
		{
			ResultSet rs = comando.executeQuery("SELECT * FROM pesquisador "
					+ condicao);
			while (rs.next())
			{
				Pesquisador pesquisador = new Pesquisador();
				pesquisador.setId(rs.getLong("id"));
				// pesquisador.setId((Long)rs.getObject("id"));
				pesquisador.setNome(rs.getString("nome"));
				// pesquisador.setNome((String)rs.getObject("nome"));
				pesquisador.setNome_cientifico(rs.getString("nome_cientifico"));
				// pesquisador.setNome_cientifico((String)rs.getObject("nome_cientifico"));
				pesquisador.setEmail(rs.getString("email"));
				// pesquisador.setEmail((String)rs.getObject("email"));
				pesquisador.setSexo(rs.getString("sexo"));
				// pesquisador.setSexo((String)rs.getObject("sexo"));
				pesquisador.setClasse(rs.getString("classe"));
				// pesquisador.setClasse((String)rs.getObject("classe"));
				pesquisador.setTitulacao(rs.getString("titulacao"));
				// pesquisador.setTitulacao((String)rs.getObject("titulacao"));
				pesquisador.setCurso_vinculado(new Curso(rs
						.getLong("curso_vinculado")));
				// pesquisador.setCurso_vinculado(new
				// CursoMysql().listar(" WHERE id ="+(rs.getLong("curso_vinculado"))).get(0));
				pesquisador.setAreaformacao(new AreaFormacao(rs
						.getLong("areaformacao")));
				// pesquisador.setAreaformacao(new
				// AreaFormacaoMysql().listar(" WHERE id ="+(rs.getLong("areaformacao"))).get(0));
				lista.add(pesquisador);
			}
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return lista;
	}
}
