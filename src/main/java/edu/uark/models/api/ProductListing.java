package edu.uark.models.api;

import java.util.LinkedList;
import java.util.List;

public class ProductListing {
	private List<Product> products;
	public List<Product> getProducts() {
		return this.products;
	}
	public ProductListing setProducts(List<Product> products) {
		this.products = products;
		return this;
	}
	public ProductListing addProduct(Product product) {
		this.products.add(product);
		return this;
	}
	
	public int getSum() {
		int sum = 0;
		for(int i = 0; i < List.length(); i++) {
			sum += List.get(i).price;
		}
		return sum;
	}
	
	public ProductListing() {
		this.products = new LinkedList<Product>();
	}
}
