package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.InstituicaoCooperadora;

public class PesquisaInstituicoesCooperadorasDAO
{

	public PesquisaInstituicoesCooperadorasDAO() {}

	public void inserir(long pesquisaId, ArrayList<InstituicaoCooperadora> instituicoesCoopreadoras) throws Exception
	{
		String sql = "INSERT INTO Pesquisainstituicoes_cooperadoras(id1, id2) VALUES(?, ?)";
		
		try
		{
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			
			for (InstituicaoCooperadora instCoop : instituicoesCoopreadoras)
			{
				stmt.setLong(1, pesquisaId);
				stmt.setLong(2, instCoop.getId());
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
		String sql = "DELETE FROM Pesquisainstituicoes_cooperadoras WHERE id1 = ?";
		
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

	public ArrayList<InstituicaoCooperadora> listar(long pesquisaId) throws Exception
	{
		String sql = "SELECT * FROM Pesquisainstituicoes_cooperadoras WHERE id1 = ?";
		ArrayList<InstituicaoCooperadora> listaInstCoop = new ArrayList<InstituicaoCooperadora>();
		
		if (pesquisaId > 0)
		{
		
			try
			{
				PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
				stmt.setLong(1, pesquisaId);
				
				ResultSet rs = stmt.executeQuery();
				InstituicaoCooperadoraDAO idao = new InstituicaoCooperadoraDAO();
				
				while (rs.next())
				{
					InstituicaoCooperadora instituicaoCooperadora = idao.listar("WHERE id = " + rs.getLong("id2")).get(0);
					listaInstCoop.add(instituicaoCooperadora);
				}
				
				rs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
		return listaInstCoop;
	}
}
