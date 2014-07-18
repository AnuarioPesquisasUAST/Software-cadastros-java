package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory
{
	// private static String endereco = "jdbc:mysql://localhost/anuario3";
	private static String endereco = "jdbc:sqlite:anuariodb.sqlite";
	private static String usuario = "root";
	private static String password = "";
	private static Connection conn;

	private ConnectionFactory() {}


	public static synchronized Connection getConnection()
	{
		
		if (conn == null)
		{
			try
			{
				// Class.forName("com.mysql.jdbc.Driver");
				// conn = DriverManager.getConnection(endereco, usuario, password);
				conn = DriverManager.getConnection(endereco);
			}
			catch (SQLException ex)
			{
				
			}
		}
		return conn;
	}

}
