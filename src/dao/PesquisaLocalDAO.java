package dao;

import modelo.Local;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
* Created by natan on 16/07/14.
*/
public class PesquisaLocalDAO
{


    ConnectionFactory conexao = null;
    private Statement comando;

    public PesquisaLocalDAO() throws Exception
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

    public void inserir(long id, ArrayList<Local> lista)
            throws Exception
    {
        for (Local x : lista)
        {
            String sql = "INSERT INTO pesquisalocal (id1,id2) VALUES("
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
        String sql = "DELETE FROM pesquisalocal WHERE id1 = "
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

    public ArrayList<Local> listar(long id) throws Exception
    {
        ArrayList<Local> lista = new ArrayList<Local>();
        if (id > 0)
        {
            String sql = "SELECT * FROM pesquisalocal WHERE id1 = "
                    + id;
            try
            {
                ResultSet rs = comando.executeQuery(sql);
                while (rs.next())
                {
                    Local x = new LocalDAO()
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
