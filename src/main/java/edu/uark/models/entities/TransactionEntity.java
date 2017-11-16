package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.models.api.Transaction;
import edu.uark.models.entities.fieldnames.TransactionFieldNames; //
import edu.uark.models.repositories.TransactionRepository; //

public class TransactionEntity extends BaseEntity<TransactionEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.recordId = rs.getInt(TransactionFieldNames.RECORD_ID);
		this.cashierId = rs.getInt(TransactionFieldNames.CASHIER_ID);
		this.referenceId = rs.getInt(TransactionFieldNames.REFERENCE_ID);
		this.totalAmount = rs.getInt(TransactionFieldNames.TOTAL_AMOUNT);
		this.type = rs.getString(TransactionFieldNames.TYPE);
		this.createdOn = rs.getTimestamp(TransactionFieldNames.CREATED_ON).toLocalDateTime();
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(TransactionFieldNames.RECORD_ID, this.recordId);
		record.put(TransactionFieldNames.CASHIER_ID, this.cashierId);
		record.put(TransactionFieldNames.REFERENCE_ID, this.referenceId);
		record.put(TransactionFieldNames.TOTAL_AMOUNT, this.totalAmount);
		record.put(TransactionFieldNames.Type, this.type);
		record.put(TransactionFieldNames.CREATED_ON, Timestamp.valueOf(this.createdOn));		

		return record;
	}

	private UUID recordId;
	public UUID getRecordId() {
		return this.id;
	}
	public TransactionEntity setRecordId(UUID id) {
		this.recordId = id;
		return this;
	}
	
	private UUID cashierId;
	public UUID getCashierId() {
		return this.id;
	}
	public TransactionEntity setCashierId(UUID id) {
		this.cashierId = id;
		return this;
	}

	private UUID referenceId;
	public UUID getReferenceId() {
		return this.id;
	}
	public TransactionEntity setReferenceId(UUID id) {
		this.referenceId = id;
		return this;
	}
	
	private int totalAmount;
	public int getTotalAmount() {
		return this.totalAmount;
	}
	public TransactionEntity setTotalAmount(int amount) {
		this.totalAmount = amount;
		return this;
	}
	
	private String type;
	public String getType() {
		return this.type;
	}
	public TransactionEntity setType(String type) {
		this.type = type;
		return this;
	}

	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public TransactionEntity setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	private TransactionEntityApiRequestStatus apiRequestStatus;
	public TransactionEntityApiRequestStatus getApiRequestStatus() {
		return this.apiRequestStatus;
	}
	public TransactionEntity setApiRequestStatus(TransactionEntityApiRequestStatus apiRequestStatus) {
		if (this.apiRequestStatus != apiRequestStatus) {
			this.apiRequestStatus = apiRequestStatus;
		}
		
		return this;
	}
	
	private String apiRequestMessage;
	public String getApiRequestMessage() {
		return this.apiRequestMessage;
	}
	public TransactionEntity setApiRequestMessage(String apiRequestMessage) {
		if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
			this.apiRequestMessage = apiRequestMessage;
		}
		
		return this;
	}
	
	public TransactionEntity() {
		super(new TransactionRepository());
		
		this.recordId = new UUID(0,0);
		this.cashierId = new UUID(0, 0);
		this.totalAmount = -1;
		this.type = StringUtils.EMPTY;
		this.referenceId = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
	}
	
	public TransactionEntity(UUID id) {
		super(id, new TransactionRepository());
		
		this.recordId = new UUID(0,0);
		this.cashierId = new UUID(0, 0);
		this.totalAmount = -1;
		this.type = StringUtils.EMPTY;
		this.referenceId = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
	}

	public TransactionEntity(Transaction apiTransaction) {
		super(apiTransaction.getId(), new TransactionRepository());

		this.recordId = apiTransaction.getRecordId();
		this.cashierId = apiTransaction.getCashierId();
		this.totalAmount = apiTransaction.getTotalAmount();
		this.type = apiTransaction.getType();
		this.referenceId = apiTransaction.getReferenceId();
		this.createdOn = LocalDateTime.now();
	}
}
