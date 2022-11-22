/**
 * 
 */
package br.com.carvalho.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;



/**
 * @author manoel.carvalho
 *
 */
public class ConnectionFactory {
	
	
	private static Connection connection;
	
	private ConnectionFactory(Connection connection) {
		
	}
	

	public static Connection getConnection() throws SQLException  {
		if (connection == null) {
			connection = initConnection();
		} else if (connection != null && connection.isClosed()) {
			connection = initConnection();
		}
		
		return connection;
	}


	private static Connection initConnection()  {
		
		 //métdo responsavel por estabelecer conexao com DB
      //  java.sql.Connection conexao = null;
        // a linha abaixo "chama" o driver
      //  String driver = "com.mysql.cj.jdbc.Driver";
        // armazenando informações referentes ao DB
        String url = "jdbc:mysql://localhost:3306/db_vendas_online?characterEncoding=utf-8";
        String user = "";
        String password = "";
        // Estabelecendo a conexao com DB
        try {
           
		//	Class.forName(driver);
			return  DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			
			throw new RuntimeException(e); 
		 }
        	
    }
	
		
	

}
