/**
 * 
 */
package br.com.carvalho.dao;

import java.util.List;

import br.com.carvalho.domain.Product;

/**
 * @author manoel.carvalho
 *
 */
public interface IProductDAO {

	Integer create(Product prod) throws Exception;

	Product read(String codigo) throws Exception;

	Integer delete(Product productDB) throws Exception;

	Integer updateProduct(Product prod) throws Exception;
	
	public List<Product> readAll() throws Exception;

}
