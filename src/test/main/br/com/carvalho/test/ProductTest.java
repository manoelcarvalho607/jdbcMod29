/**
 * 
 */
package br.com.carvalho.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import br.com.carvalho.dao.IProductDAO;
import br.com.carvalho.dao.ProductDAO;
import br.com.carvalho.domain.Product;



/**
 * @author manoel.carvalho
 *
 */

public class ProductTest {
	
	private IProductDAO productDAO;
	Product prod = new Product();
	
	@Test
	public void crudProductTest() throws Exception {
		productDAO = new ProductDAO();
		
		
		prod.setNomeProduto("caderno");
		prod.setValorUnit(8.50);
		prod.setQdt(20);
		prod.setCodigo("10");
		
		Integer newProd = productDAO.create(prod);
		assertTrue(newProd == 1);
		
		Product productDB = productDAO.read("10");
		assertNotNull(productDB);
		
		assertEquals(prod.getCodigo(), productDB.getCodigo());
		assertEquals(prod.getNomeProduto(), productDB.getNomeProduto());
		
		Long idProd = productDB.getId();
		
		prod.setId(idProd);
		prod.setNomeProduto("caderno branco e preto");
		prod.setValorUnit(8.50);
		prod.setQdt(20);
		prod.setCodigo("10");
		
		 Integer up = productDAO.updateProduct(prod);
		 assertTrue(up == 1);
		
		Integer countDel = productDAO.delete(productDB);
		assertTrue(countDel == 1);
		
	}
	


}
