package edu.uark.commands.transaction;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.Transaction;
import edu.uark.models.api.enums.TransactionApiRequestStatus;
import edu.uark.models.entities.TransactionEntity;
import edu.uark.models.repositories.TransactionRepository;
import edu.uark.models.repositories.interfaces.TransactionRepositoryInterface;

public class TransactionSaveCommand implements ResultCommandInterface<Transaction> {
	@Override
	public Transaction execute() {
		
		TransactionEntity transactionEntity;
		if (StringUtils.isBlank(this.apiTransaction.getTransactionId())) {
			String newRecordId;
			do {
				newRecordId = RandomStringUtils.randomNumeric(RECORD_ID_LENGTH);
			} while (this.transactionRepository.transactionIdExists(newRecordId));

			this.apiTransaction.setRecordId(newRecordId);
			transactionEntity = new TransactionEntity(this.apiTransaction);
		} else {
			transactionEntity = this.transactionRepository.get(this.apiTransaction.getRecordId());
			if (transactionEntity != null) {
				this.apiTransaction = transactionEntity.synchronize(this.apiTransaction);
			} else {
				transactionEntity = this.transactionRepository.byRecordId/**/(this.apiTransaction.getRecordId());
				if (transactionEntity == null) {
					transactionEntity = new transactionEntity(this.apiTransaction);
				} else {
					return (new Transaction()).setApiRequestStatus(TransactionApiRequestStatus.TRANSACTION_ID_ALREADY_EXISTS);
				}
			}
		}
		
		transactionEntity.save();
		if ((new UUID(0, 0)).equals(this.apiTransaction.getRecordId())) {
			this.apiTransaction.setRecordId(transactionEntity.getRecordId());
		}
		
		return this.apiTransaction;
	}
	
	//Properties
	private Transaction apiTransaction;
	public Transaction getApiTransaction() {
		return this.apiTransaction;
	}
	public TransactionSaveCommand setApiTransaction(Transaction apiTransaction) {
		this.apiTransaction = apiTransaction;
		return this;
	}
	
	private TransactionRepositoryInterface transactionRepository;
	public TransactionRepositoryInterface getTransactionRepository() {
		return this.transactionRepository;
	}
	public TransactionSaveCommand setTransactionRepository(TransactionRepositoryInterface transactionRepository) {
		this.transactionRepository = transactionRepository;
		return this;
	}
	
	private static final int Transaction_ID_LENGTH = 4;
	
	public TransactionSaveCommand() {
		this.transactionRepository = new TransactionRepository();
	}
}
