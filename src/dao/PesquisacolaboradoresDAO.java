package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Pesquisador;

public class PesquisacolaboradoresDAO
{
	public PesquisacolaboradoresDAO() {}

	public void inserir(long idPesquisa, ArrayList<Pesquisador> pesquisadores) throws Exception
	{
		String sql = "INSERT INTO Pesquisacolaboradores (id1,id2) VALUES(?, ?)";
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
		
		for (Pesquisador pesquisador : pesquisadores)
		{
			stmt.setLong(1, idPesquisa);
			stmt.setLong(2, pesquisador.getId());
			
			try
			{
				stmt.execute();
			}
			catch (SQLException e)
			{
				throw e;
			}
			
			stmt.clearParameters();
		}
	}

	public void remover(long id) throws Exception
	{
		String sql = "DELETE FROM Pesquisacolaboradores WHERE id1 = ?";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setLong(1, id);
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	public ArrayList<Pesquisador> listar(long id) throws Exception
	{
		ArrayList<Pesquisador> listaPesquisador = new ArrayList<Pesquisador>();
		
		if (id > 0)
		{
			String sql = "SELECT * FROM Pesquisacolaboradores WHERE id1 = ?";
			
			try
			{
				PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();
				
				PesquisadorDAO pdao = new PesquisadorDAO();
				
				while (rs.next())
				{
					Pesquisador pesquisador = pdao.listar("WHERE id= " + rs.getLong("id2")).get(0);
					listaPesquisador.add(pesquisador);
				}
				
				rs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
		
		return listaPesquisador;
	}
}
