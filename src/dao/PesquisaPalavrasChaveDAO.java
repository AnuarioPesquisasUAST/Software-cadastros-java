package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.*;

public class PesquisaPalavrasChaveDAO
{
	ConnectionFactory conexao = null;
	private Statement comando;

	public PesquisaPalavrasChaveDAO() throws Exception
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

	public void inserir(long id, ArrayList<PalavraChave> lista)
			throws Exception
	{
		for (PalavraChave x : lista)
		{
			String sql = "INSERT INTO Pesquisapalavras_chave (id1,id2) VALUES("
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
		String sql = "DELETE FROM Pesquisapalavras_chave WHERE id1 = " + id;
		try
		{
			comando.execute(sql.toString());
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<PalavraChave> listar(long id) throws Exception
	{
		ArrayList<PalavraChave> lista = new ArrayList<PalavraChave>();
		if (id > 0)
		{
			String sql = "SELECT * FROM Pesquisapalavras_chave WHERE id1 = "
					+ id;
			try
			{
				ResultSet rs = comando.executeQuery(sql);
				while (rs.next())
				{
					PalavraChave x = new PalavraChaveDAO().listar(
							" WHERE id=" + (rs.getLong("id2"))).get(0);
					lista.add(x);
					// lista.add(new PalavraChave(rs.getLong("id2")));
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
