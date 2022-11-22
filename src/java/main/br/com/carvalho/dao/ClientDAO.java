/**
 * 
 */
package br.com.carvalho.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.carvalho.domain.Client;
import br.com.carvalho.jdbc.ConnectionFactory;

/**
 * @author manoel.carvalho
 *
 */
public class ClientDAO implements IClientDAO {

	@Override
	public Integer create(Client client) throws Exception {
		
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlInsert();
			stm = connection.prepareStatement(sql);
			addParametersInsert(stm, client);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		finally {
			closeConnection(connection, stm, null);
		}
		
	}
	
	
	@Override
	public Client read(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Client client = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelect();
			
			
			
			stm = connection.prepareStatement(sql);
			addParametersSelect(stm, codigo);
			rs = stm.executeQuery();
			
			if (rs.next()) {
				client = new Client();
				Long id = rs.getLong("ID");
				String nome = rs.getString("NOME");
				String cod = rs.getString("CODIGO");
				client.setId(id);
				client.setNome(nome);
				client.setCodigo(cod);
				
				}
			} catch (Exception e) {
				throw e;
			} finally {
				closeConnection(connection, stm, rs);
			}
		
		return client;
	}
	
	
	@Override
	public Integer update(Client client) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlUpdate();
			stm = connection.prepareStatement(sql);
			addParametersUpdate(stm, client);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		finally {
			closeConnection(connection, stm, null);
		}
		
	}
	
	
	@Override
	public Integer delete(Client client) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlDelete();
			stm = connection.prepareStatement(sql);
			addParametersDelete(stm, client);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		finally {
			closeConnection(connection, stm, null);
		}
	
	}
	
	
	@Override
	public List<Client> readAll() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Client> list = new ArrayList<>();
		Client client = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelectAll();
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				client = new Client();
				Long id = rs.getLong("ID");
				String nome = rs.getString("NOME");
				String cod = rs.getString("CODIGO");
				client.setId(id);
				client.setNome(nome);
				client.setCodigo(cod);
				list.add(client);
				}
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, rs);
		}
		
		return list;
	}
	

	
	private String getSqlInsert() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_CLIENT (NOME, CODIGO)");
		sb.append("VALUES (?,?)");
		return sb.toString();
	}
	
	private void addParametersInsert(PreparedStatement stm, Client client) throws SQLException {
		stm.setString(1, client.getNome());
		stm.setString(2, client.getCodigo());
		
	}

	private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	private void addParametersUpdate(PreparedStatement stm, Client client) throws SQLException {
		stm.setString(1, client.getNome());
		stm.setString(2, client.getCodigo());
		stm.setLong(3, client.getId());
		
	}
	
	private String getSqlUpdate() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_CLIENT");
		sb.append(" SET NOME = ?, CODIGO = ?");
		sb.append(" WHERE ID = ?");
		return sb.toString();
	}
	
	private String getSqlSelect() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_CLIENT");
		sb.append(" WHERE CODIGO = ?");
		return sb.toString();
	}


	private void addParametersSelect(PreparedStatement stm, String codigo) throws SQLException {
		stm.setString(1, codigo);
		
	}




	

	private void addParametersDelete(PreparedStatement stm, Client client) throws SQLException {
		stm.setString(1, client.getCodigo());
		
	}


	private String getSqlDelete() {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM TB_CLIENT");
		sb.append(" WHERE CODIGO = ?");
		return sb.toString();
	}


	private String getSqlSelectAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_CLIENT");
		return sb.toString();
	}
	






}
