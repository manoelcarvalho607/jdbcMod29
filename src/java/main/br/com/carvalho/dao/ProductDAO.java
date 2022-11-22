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
import br.com.carvalho.domain.Product;
import br.com.carvalho.jdbc.ConnectionFactory;

/**
 * @author manoel.carvalho
 *
 */
public class ProductDAO implements IProductDAO {

	@Override
	public Integer create(Product prod) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlInsert();
			stm = connection.prepareStatement(sql);
			addParametersInsert(stm, prod);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		finally {
			closeConnection(connection, stm, null);
		}
		
	}
	

	@Override
	public Product read(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Product product = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelect();
			
			stm = connection.prepareStatement(sql);
			addParametersSelect(stm, codigo);
			rs = stm.executeQuery();
			
			if (rs.next()) {
				product = new Product();
				Long id = rs.getLong("ID");
				String nome = rs.getString("NOME_PROD");
				double vUnit = rs.getDouble("VALOR_UNIT");
				 int qtd = rs.getInt("QTD");
				String cod = rs.getString("COD_PROD");
				product.setId(id);
				product.setNomeProduto(nome);
				product.setValorUnit(vUnit);
				product.setQdt(qtd);
				product.setCodigo(cod);
				
				}
			} catch (Exception e) {
				throw e;
			} finally {
				closeConnection(connection, stm, rs);
			}
		
		return product;
		
	}
	
	@Override
	public List<Product> readAll() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<>();
		Product product = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelectAll();
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				product = new Product();
				Long id = rs.getLong("ID");
				String nome = rs.getString("NOME_PROD");
				double valorUnit = rs.getDouble("VALOR_UNIT");
				 int qtd = rs.getInt("QTD");
				String cod = rs.getString("COD_PROD");
				product.setId(id);
				product.setNomeProduto(nome);
				product.setValorUnit(valorUnit);
				product.setQdt(qtd);
				product.setCodigo(cod);
				
				list.add(product);
				
				}
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, rs);
		}
		
		return list;
	
	}


	

	
	@Override
	public Integer delete(Product product) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlDelete();
			stm = connection.prepareStatement(sql);
			addParametersDelete(stm, product );
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		finally {
			closeConnection(connection, stm, null);
		}
		
	}


	
	@Override
	public Integer updateProduct(Product prod) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlUpdate();
			stm = connection.prepareStatement(sql);
			addParametersUpdate(stm, prod);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		finally {
			closeConnection(connection, stm, null);
		}
		
	}

	





	private String getSqlInsert() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_PRODUCTS (NOME_PROD,VALOR_UNIT,QTD,COD_PROD)");
		sb.append("VALUES (?,?,?,?)");
		return sb.toString();
	}
	
	private void addParametersInsert(PreparedStatement stm, Product prod) throws SQLException {
		stm.setString(1, prod.getNomeProduto());
		stm.setDouble(2, prod.getValorUnit());
		stm.setInt(3, prod.getQdt());
		stm.setString(4,prod.getCodigo());
		
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


	
	private String getSqlSelect() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_PRODUCTS");
		sb.append(" WHERE COD_PROD = ?");
		return sb.toString();
	}


	private void addParametersSelect(PreparedStatement stm, String codigo) throws SQLException {
		stm.setString(1, codigo);
		
	}

	
	private String getSqlDelete() {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM TB_PRODUCTS");
		sb.append(" WHERE COD_PROD = ?");
		return sb.toString();
	}

	
	private void addParametersDelete(PreparedStatement stm, Product product) throws SQLException {
		stm.setString(1, product.getCodigo());
		
	}
	
	private String getSqlUpdate() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_PRODUCTS");
		sb.append(" SET NOME_PROD = ?, VALOR_UNIT = ?, QTD = ?, COD_PROD = ?");
		sb.append(" WHERE ID = ?");
		return sb.toString();
	}



	private void addParametersUpdate(PreparedStatement stm, Product prod) throws SQLException {
		stm.setString(1, prod.getNomeProduto());
		stm.setDouble(2, prod.getValorUnit());
		stm.setInt(3, prod.getQdt());
		stm.setString(4, prod.getCodigo());
		stm.setLong(5, prod.getId());
		
	}


	

	private String getSqlSelectAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_PRODUCTS");
		return sb.toString();
		
	}




	
	
}
