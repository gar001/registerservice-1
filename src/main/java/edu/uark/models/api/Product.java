package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.models.api.enums.ProductApiRequestStatus;
import edu.uark.models.entities.ProductEntity;

public class Product {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public Product setId(UUID id) {
		this.id = id;
		return this;
	}
	
	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public Product setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}
	
	private int quantity;
	public int getQuantity() {
		return this.quantity;
	}
	public Product setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}
	
	private int price;
	public int getPrice() {
		return this.price;
	}
	public Product setPrice(int price) {
		this.price = price;
		return this;
	}
	
	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public Product setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	private ProductApiRequestStatus apiRequestStatus;
	public ProductApiRequestStatus getApiRequestStatus() {
		return this.apiRequestStatus;
	}
	public Product setApiRequestStatus(ProductApiRequestStatus apiRequestStatus) {
		if (this.apiRequestStatus != apiRequestStatus) {
			this.apiRequestStatus = apiRequestStatus;
		}
		
		return this;
	}
	
	private String apiRequestMessage;
	public String getApiRequestMessage() {
		return this.apiRequestMessage;
	}
	public Product setApiRequestMessage(String apiRequestMessage) {
		if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
			this.apiRequestMessage = apiRequestMessage;
		}
		
		return this;
	}
	
	public Product() {
		this.quantity = -1;
		this.price = -1;
		this.lookupCode = "";
		this.id = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = ProductApiRequestStatus.OK;
	}
	
	public Product(Product copiedProduct) {
		this.quantity = copiedProduct.getQuantity();
		this.price = copiedProduct.getPrice();
		this.lookupCode = copiedProduct.getLookUpCode();
		this.id = copiedProduct.getId();
		this.createdOn = copiedProduct.getCreatedOn();
		this.apiRequestMessage = copiedProduct.getApiRequestMessage();
		this.apiRequestStatus = copiedProduct.getApiRequestStatus();
	}
	
	public Product(ProductEntity productEntity) {
		this.id = productEntity.getId();
		this.quantity = productEntity.getQuantity();
		this.price = productEntity.getPrice();
		this.createdOn = productEntity.getCreatedOn();
		this.lookupCode = productEntity.getLookupCode();

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = ProductApiRequestStatus.OK;
	}
}
