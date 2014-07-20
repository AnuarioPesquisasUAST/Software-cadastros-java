package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Local;

/**
* Created by natan on 16/07/14.
*/
public class PesquisaLocalDAO
{

    public PesquisaLocalDAO() {}

    public void inserir(long pesquisaId, ArrayList<Local> locais) throws Exception
    {
    	String sql = "INSERT INTO pesquisalocal(id1,id2) VALUES (?, ?)";
    	
    	try
    	{
    		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
    		
	        for (Local local : locais)
	        {
	        	stmt.setLong(1, pesquisaId);
	        	stmt.setLong(2, local.getId());
	        	
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
        String sql = "DELETE FROM pesquisalocal WHERE id1 = ?";
        
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

    public ArrayList<Local> listar(long pesquisaId) throws Exception
    {
    	String sql = "SELECT * FROM pesquisalocal WHERE id1 = ?";
        ArrayList<Local> listaLocal = new ArrayList<Local>();
        
        if (pesquisaId > 0)
        {
            try
            {
            	PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
            	stmt.setLong(1, pesquisaId);
                ResultSet rs = stmt.executeQuery();
                LocalDAO ldao = new LocalDAO();
                
                while (rs.next())
                {
                    Local local = ldao.listar(" WHERE id = " + rs.getLong("id2")).get(0);
                    listaLocal.add(local);
                }
                
                rs.close();
            }
            catch (SQLException e)
            {
                throw e;
            }
        }
        
        return listaLocal;
    }
}
