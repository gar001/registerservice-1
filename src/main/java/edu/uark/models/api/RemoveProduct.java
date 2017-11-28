package edu.uark.models.api;

import org.apache.commons.lang3.StringUtils;

public class RemoveProduct {
	private String productLookUpCode;
	public String getProductLookUpCode() {
		return this.productLookUpCode;
	}
	public EmployeeLogin setProductLookUpCode(String productLookUpCode) {
		this.productId = productId;
		return this;
	}
	private TransactionEntry transactionEntry;
	public TransactionEntry getTransactionEntry() {
		return this.transactionEntry;
	}
	public EmployeeLogin setTransactionEntry(TransactionEntry transactionEntry) {
		this.transactionEntry = new TransactionEntry(transactionEntry);
		return this;
	}
	
	public EmployeeLogin() {
		this.productLookUpCode = StringUtils.EMPTY;
		this.transactionEntry = new TransactionEntry();
	}
}


