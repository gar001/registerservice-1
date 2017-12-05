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

public class CreateTransactionCommand {

	public Transaction execute() {
		return this.apiTransactionEntry.createTransaction();
	}

	private TransactionEntry apiTransactionEntry
	public TransactionEntry getApiTransactionEntry() {
		return this.apiTransactionEntry;
	}
	public TransactionSaveCommand setApiTransactionEntry(TransactionEntry apiTransactionEntry) {
		this.apiTransactionEntry = apiTransactionEntry;
		return this;
	}
}
