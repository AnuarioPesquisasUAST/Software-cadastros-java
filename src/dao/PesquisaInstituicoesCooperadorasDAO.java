package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.*;

public class PesquisaInstituicoesCooperadorasDAO
{
	ConnectionFactory conexao = null;
	private Statement comando;

	public PesquisaInstituicoesCooperadorasDAO() throws Exception
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

	public void inserir(long id, ArrayList<InstituicaoCooperadora> lista)
			throws Exception
	{
		for (InstituicaoCooperadora x : lista)
		{
			String sql = "INSERT INTO Pesquisainstituicoes_cooperadoras (id1,id2) VALUES("
					+ id + "," + x.getId() + ")";
			try
			{
				comando.execute(sql.toString());
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
	}

	public void remover(long id) throws Exception
	{
		String sql = "DELETE FROM Pesquisainstituicoes_cooperadoras WHERE id1 = "
				+ id;
		try
		{
			comando.execute(sql.toString());
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<InstituicaoCooperadora> listar(long id) throws Exception
	{
		ArrayList<InstituicaoCooperadora> lista = new ArrayList<InstituicaoCooperadora>();
		if (id > 0)
		{
			String sql = "SELECT * FROM Pesquisainstituicoes_cooperadoras WHERE id1 = "
					+ id;
			try
			{
				ResultSet rs = comando.executeQuery(sql);
				while (rs.next())
				{
					InstituicaoCooperadora x = new InstituicaoCooperadoraDAO()
							.listar(" WHERE id=" + (rs.getLong("id2"))).get(0);
					lista.add(x);
					// lista.add(new InstituicaoCooperadora(rs.getLong("id2")));
				}
				rs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
		return lista;
	}
}
