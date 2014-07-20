package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.PalavraChave;

public class PesquisaPalavrasChaveDAO
{

	public PesquisaPalavrasChaveDAO() {}

	public void inserir(long pesquisaId, ArrayList<PalavraChave> palavrasChave) throws Exception
	{
		String sql = "INSERT INTO Pesquisapalavras_chave(id1, id2) VALUES (?, ?)";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			
			for (PalavraChave palavraChave : palavrasChave)
			{
					stmt.setLong(1, pesquisaId);
					stmt.setLong(2, palavraChave.getId());
					stmt.execute();
					stmt.clearParameters();
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public void remover(long pesquisaId) throws Exception
	{
		String sql = "DELETE FROM Pesquisapalavras_chave WHERE id1 = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, pesquisaId);
			stmt.execute();
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<PalavraChave> listar(long pesquisaId) throws Exception
	{
		String sql = "SELECT * FROM Pesquisapalavras_chave WHERE id1 = ?";
		ArrayList<PalavraChave> listaPalavraChave = new ArrayList<PalavraChave>();
		
		if (pesquisaId > 0)
		{
			
			try
			{
				PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
				stmt.setLong(1, pesquisaId);
				ResultSet rs = stmt.executeQuery();
				PalavraChaveDAO pcdao = new PalavraChaveDAO();
				
				while (rs.next())
				{
					PalavraChave palavraChave = pcdao.listar("WHERE id = " + rs.getLong("id2")).get(0);
					listaPalavraChave.add(palavraChave);
				}
				
				rs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
		
		return listaPalavraChave;
	}
}
