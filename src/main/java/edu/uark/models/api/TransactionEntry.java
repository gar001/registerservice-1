package edu.uark.models.api;

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

	private ProductListing productListing;	
	public UUID getProducts() {
		return this.productListing.getProducts();
	}
	public TransactionEntry setProducts(ProductListing products) {
		this.productListing.getProducts().clear();
		Product input;
		for (int i = 0; i < products.size(); i++) {
			input = new Product();
			input = productListing.getProducts().get(i);
			this.productListing.addProduct(input);
		}
		return this;
	}
	public TransactionEntry setProducts(List<Product> products) {
		this.productListing.getProducts().clear();
		Product input;
		for (int i = 0; i < products.size(); i++) {
			input = new Product();
			input = products.get(i);
			this.productListing.addProduct(input);
		}
		return this;
	}

	public TransactionEntry addProduct(Product product) {
		int index = this.productListing.getProducts().indexOf(product);
		int quantity;
		if (index == -1){
			this.productListing.addProduct(product);
			return this;
		} else {
			quantity = this.productListing.getProducts().get(index).getQuantity();
			this.productListing.getProducts().get(index).set(quantity + 1);
			return this;
		}
	} 
	
	public TransactionEntry removeProduct(Product product) {
		int index = this.getProducts().indexOf(product);
		int quantity; this.getProducts().get(index).getQuantity();
		if (index == -1){
			return this;
		} else {
			quantity = this.productListing.getProducts().get(index).getQuantity();
			if (quantity > 1) {
				this.productListing.getProducts().get(index).setQuantity(quantity - 1);
				return this;
			} else { 
				this.productListing.getProducts().remove(index);
				return this;
			}
		}	
	} 
	
	public Transaction createTransaction (UUID cashierId, String type, UUID referenceId) {
		Transaction transaction = new Transaction();
		transaction.setRecordId(this.id);
		transaction.setCashierId(cashierId);
		transaction.setTotalAmount(this.products.getSum());
		transaction.setType(type);
		transaction.setReferenceId(referenceId);
		return transaction;
	}

	public TransactionEntry() {
		this.Id = new UUID(0, 0);
		this.products = new ProductListing();
	}
	
	public TransactionEntry(TransactionEntry transactionEntry) {
		this.Id = transactionEntry.getId();
		this.products = new ProductListing();
		this.setProducts(transactionEntry.getProducts());

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
	
	public TransactionEntry(TransactionEntryEntity transactionEntryEntity) {
		this.Id = transactionEntryEntity.getId();
		this.products = new ProductListing();
		this.setProducts(transactionEntryEntity.getProducts());

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
}
