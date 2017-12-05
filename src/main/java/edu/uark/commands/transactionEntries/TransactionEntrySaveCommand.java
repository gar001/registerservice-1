package edu.uark.commands.transactionEntry;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.Transaction;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.api.enums.TransactionEntryApiRequestStatus; //
import edu.uark.models.entities.TransactionEntryEntity;
import edu.uark.models.repositories.TransactionEntryRepository;
import edu.uark.models.repositories.interfaces.TransactionEntryRepositoryInterface;

public class TransactionEntrySaveCommand implements ResultCommandInterface<TransactionEntry> {
	@Override
	public TransactionEntry execute() {
		
		TransactionEntity transactionEntity;
		if (StringUtils.isBlank(this.apiTransactionEntry.getId())) {
			String newId;
			do {
				newId = RandomStringUtils.randomNumeric(ID_LENGTH);
			} while (this.transactionEntryRepository.exists(newId));

			this.apiTransactionEntry.setId(newId);
			transactionEntryEntity = new TransactionEntryEntity(this.apiTransactionEntry);
		} else {
			transactionEntryEntity = this.transactionEntryRepository.get(this.apiTransactionEntry.getId());
			if (transactionEntity != null) {
				this.apiTransactionEntry = transactionEntryEntity.synchronize(this.apiTransactionEntry);
			} else {
				transactionEntryEntity = this.transactionEntryRepository.byId/**/(this.apiTransactionEntry.getId());
				if (transactionEntryEntity == null) {
					transactionEntryEntity = new transactionEntryEntity(this.apiTransactionEntry);
				} else {
					return (new TransactionEntry()).setApiRequestStatus(TransactionEntryApiRequestStatus.ID_ALREADY_EXISTS);
				}
			}
		}
		
		transactionEntity.save();
		if ((new UUID(0, 0)).equals(this.apiTransactionEntry.getId())) {
			this.apiTransactionEntry.setId(transactionEntryEntity.getId());
		}
		
		return this.apiTransactionEntry;
	}
	
	//Properties
	private TransactionEntry apiTransactionEntry;
	public TransactionEntry getApiTransactionEntry() {
		return this.apiTransactionEntry;
	}
	public TransactionSaveCommand setApiTransactionEntry(TransactionEntry apiTransactionEntry) {
		this.apiTransactionEntry = apiTransactionEntry;
		return this;
	}
	
	private TransactionEntryRepositoryInterface transactionEntryRepository;
	public TransactionEntryRepositoryInterface getTransactionEntryRepository() {
		return this.transactionEntryRepository;
	}
	public TransactionEntrySaveCommand setTransactionEntryRepository(TransactionEntryRepositoryInterface transactionEntryRepository) {
		this.transactionEntryRepository = transactionEntryRepository;
		return this;
	}
	
	private static final int ID_LENGTH = 4;
	
	public TransactionEntrySaveCommand() {
		this.transactionEntryRepository = new TransactionEntryRepository();
	}
}
