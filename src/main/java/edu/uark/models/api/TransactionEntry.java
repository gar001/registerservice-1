package edu.uark.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;


import edu.uark.models.api.ProductListing;
import edu.uark.models.api.Product;
import edu.uark.models.api.enums.TransactionEntryApiRequestStatus; //
import edu.uark.models.entities.TransactionEntryEntity;

public class TransactionEntry {
	private UUID Id;
	public UUID getId() {
		return this.id;
	}
	public TransactionEntry setId(UUID id) {
		this.recordId = id;
		return this;
	}

	private ProductListing products;	
	public UUID getProducts() {
		return this.products;
	}
	public TransactionEntry setProducts(ProductListing products) {
		this.products.clear();
		Product input = new Product();
		for (int i = 0; i < products.size(); i++) {
			input = products.get(i);
			this.products.add(input);
		}
		return this;
	}

	public TransactionEntry addProduct(Product product) {
		this.products.add(product);
		return this;
	} 


	public int 	
	
	public Transaction() {
		this.Id = new UUID(0, 0);
		this.products = new ProductListing();
	}
	
	public Transaction(TransactionEntity transactionEntity) {
		this.Id = transactionEntryEntity.getId();
		this.products = transactionEntryEntity.getProducts();

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
}