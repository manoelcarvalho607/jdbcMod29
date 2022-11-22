/**
 * 
 */
package br.com.carvalho.dao;

import java.util.List;

import br.com.carvalho.domain.Client;

/**
 * @author manoel.carvalho
 *
 */
public interface IClientDAO {
	
	public Integer create(Client client) throws Exception;

	public Client read(String codigo) throws Exception;
	
	public Integer update(Client client) throws Exception;
	
	public Integer delete(Client client) throws Exception;
	
	public List<Client> readAll() throws Exception;
}
