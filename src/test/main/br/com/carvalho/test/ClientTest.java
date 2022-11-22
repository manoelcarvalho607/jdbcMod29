/**
 * 
 */
package br.com.carvalho.test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import br.com.carvalho.dao.IClientDAO;
import br.com.carvalho.dao.ClientDAO;
import br.com.carvalho.domain.Client;

/**
 * @author manoel.carvalho
 *
 */
public class ClientTest {
	
	private IClientDAO clientDao;
	
	
	

	@Test
	public void crudClientTest() throws Exception {
		clientDao = new ClientDAO();
		
		Client client = new Client();
		
		client.setCodigo("11");
		client.setNome("João Silva");
		Integer countCad = clientDao.create(client);
		assertTrue(countCad == 1);
		
		
	
		Client clientDB = clientDao.read("11");
		assertNotNull(clientDB);
		
		
		assertEquals(client.getCodigo(), clientDB.getCodigo());
		assertEquals(client.getNome(), clientDB.getNome());
		
		Long idClient = clientDB.getId();
		System.out.println(idClient);
		
		client.setId(idClient);
		client.setNome("João Silva carvalho");
		client.setCodigo("11");
		
		Integer up = clientDao.update(client);
		assertTrue(up == 1);
		
		
		Integer countDel = clientDao.delete(clientDB);
		assertTrue(countDel == 1);
	
	}
	
	

}
