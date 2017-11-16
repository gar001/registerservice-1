package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.models.api.enums.TransactionApiRequestStatus; //
import edu.uark.models.entities.TransactionEntity;

public class Transaction {
	private UUID recordId;
	public UUID getRecordId() {
		return this.id;
	}
	public Transaction setRecordId(UUID id) {
		this.recordId = id;
		return this;
	}
	
	private UUID cashierId;
	public UUID getCashierId() {
		return this.id;
	}
	public Transaction setCashierId(UUID id) {
		this.cashierId = id;
		return this;
	}

	private UUID referenceId;
	public UUID getReferenceId() {
		return this.id;
	}
	public Transaction setReferenceId(UUID id) {
		this.referenceId = id;
		return this;
	}
	
	private int totalAmount;
	public int getTotalAmount() {
		return this.totalAmount;
	}
	public Transaction setTotalAmount(int amount) {
		this.totalAmount = amount;
		return this;
	}
	
	private String type;
	public String getType() {
		return this.type;
	}
	public Transaction setType(String type) {
		this.type = type;
		return this;
	}

	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public Transaction setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	private TransactionApiRequestStatus apiRequestStatus;
	public TransactionApiRequestStatus getApiRequestStatus() {
		return this.apiRequestStatus;
	}
	public Transaction setApiRequestStatus(TransactionApiRequestStatus apiRequestStatus) {
		if (this.apiRequestStatus != apiRequestStatus) {
			this.apiRequestStatus = apiRequestStatus;
		}
		
		return this;
	}
	
	private String apiRequestMessage;
	public String getApiRequestMessage() {
		return this.apiRequestMessage;
	}
	public Transaction setApiRequestMessage(String apiRequestMessage) {
		if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
			this.apiRequestMessage = apiRequestMessage;
		}
		
		return this;
	}
	
	public Transaction() {
		this.recordId = new UUID(0, 0);
		this.cashierId = new UUID(0, 0);
		this.totaAmount = -1;
		this.type = StringUtils.EMPTY;
		this.referenceID = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
	
	public Transaction(TransactionEntity transactionEntity) {
		this.recordId = transactionEntity.getRecordId();
		this.cashierId = transactionEntity.getCashierId();
		this.totalAmount = transactionEntity.getTotalAmount();
		this.type = transactionEntity.getType();
		this.referenceId = transactionEntity.getReferenceId();
		this.createdOn = transactionEntity.getCreatedOn();

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
}
