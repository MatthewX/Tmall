package tmall.bean;

import java.util.List;

public class Category {
	
	private String name;
	private int id;
	private List<Product> products;
	private List<List<Product>> productsByRows;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<List<Product>> getProductsByRows() {
		return productsByRows;
	}
	public void setProductsByRows(List<List<Product>> productsByRows) {
		this.productsByRows = productsByRows;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + "]";
	}
	
	
	
	
}
