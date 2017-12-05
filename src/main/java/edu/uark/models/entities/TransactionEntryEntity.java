package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;


import edu.uark.models.api.ProductListing;
import edu.uark.models.api.Product;
import edu.uark.models.api.enums.TransactionEntryApiRequestStatus; //
import edu.uark.models.entities.TransactionEntryEntity;

public class TransactionEntry {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.id = rs.getInt(TransactionFieldNames.ID);
		String encodedString = rs.getString(TransactionFieldNames.PRODUCT_LISTING);
		this.products = listingFromEncodedString(encodedString);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(TransactionFieldNames.ID, this.id);
		record.put(TransactionFieldNames.PRODUCT_LISTING, this.listingToEncodedString());		

		return record;
	}

	public String listingToEncodedString() {
		Product holder = new Product();
		List<Product> holderList = this.products.getProducts();
		for(int x = 0; x < holderList.size(); x++) {
			encodedString += (holderList.get(x).getId() + "|");
			encodedString += (holderList.get(x).getLookUpCode() + "|");
			encodedString += (holderList.get(x).getQuantity() + "|");
			encodedString += (holderList.get(x).getPrice() + "|");
			encodedString += (holderList.get(x).getCreatedOn().toString() + "|");
		}

		return encodedString;
	}

	public listingFromEncodedString(String encodedString) {
		String[] rawTransactionEntry = encodedString.split("|");
		List<Product> holderList = new List<Product>();
		int price;
		int quantity;
		UUID id = new UUID(0,0);
		for (int x = 0; String[x] != ""; x++) {
			Product holderProduct = new Product();
			id = fromString(encodedString[x]); x++;
			Product.setId(id);
 
			holderProduct.setId(encodedString[x]); x++;
			holderProduct.setLookUpCode(encodedString[x]); x++;

			quantity = decode(encodedString[x]);x++;
			holderProduct.setQuantity(quantity); x++;

			price = decode(encodedString[x]);x++;
			holderProduct.setPrice(price); 

			LocalDateTime createdOn = parse(encodedString[x]);x++;
			holderProduct.setCreatedOn(createdOn); 
			holderList.add(holderProduct);
		} 
		ProductListing productListing = new ProductListing();
		for (int x = 0; x < holderList.size(); x++) {
			productListing.add(holderList.get(x))
		}
		return productListing;
	}

	private UUID id;
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

	public TransactionEntryEntity() {
		this.Id = new UUID(0, 0);
		this.products = new ProductListing();
	}
	
	public TransactionEntryEntity(TransactionEntryEntity transactionEntryEntity) {
		this.Id = transactionEntryEntity.getId();
		this.products = new ProductListing(transactionEntryEntity.getProducts());
	}
	
	public TransactionEntryEntity(TransactionEntry transactionEntry) {
		this.Id = transactionEntryEntity.getId();
		this.products = new ProductListing(transactionEntry.getProducts());
	}
}
