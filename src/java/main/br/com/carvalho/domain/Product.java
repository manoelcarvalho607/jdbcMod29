/**
 * 
 */
package br.com.carvalho.domain;

/**
 * @author manoel.carvalho
 *
 */
public class Product {
	
	private Long id;
	private String nomeProduto;
	private double valorUnit;
	private Integer qdt;
	private String codigo;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getValorUnit() {
		return valorUnit;
	}
	public void setValorUnit(double valorUnit) {
		this.valorUnit = valorUnit;
	}
	public Integer getQdt() {
		return qdt;
	}
	public void setQdt(Integer qdt) {
		this.qdt = qdt;
	} 
	
	

}
