package edu.uark.models.repositories;

import java.sql.SQLException;

import edu.uark.dataaccess.repository.BaseRepository;
import edu.uark.dataaccess.repository.DatabaseTable;
import edu.uark.dataaccess.repository.helpers.PostgreFunctionType;
import edu.uark.dataaccess.repository.helpers.SQLComparisonType;
import edu.uark.dataaccess.repository.helpers.where.WhereClause;
import edu.uark.dataaccess.repository.helpers.where.WhereContainer;
import edu.uark.models.entities.TransactionEntryEntity;
import edu.uark.models.entities.fieldnames.TransactionEntryFieldNames; //
import edu.uark.models.repositories.interfaces.TransactionEntryRepositoryInterface; //

public class TransactionEntryRepository extends BaseRepository<TransactionEntryEntity> implements TransactionEntryRepositoryInterface {
	
	@Override
	public boolean transactionExists(String id) {
		return this.existsWhere(
			new WhereContainer(
				(new WhereClause()).
					table(this.primaryTable).
					fieldName(TransactionFieldName.ID).
					comparison(SQLComparisonType.EQUALS)
			),
			(ps) -> {
				try {
					ps.setObject(1, id);
				} catch (SQLException e) {}

				return ps;
			}
		);
}
	
	@Override
	public TransactionEntity createOne() {
		return new TransactionEntity();
	}
	
	public TransactionRepository() {
		super(DatabaseTable.TRANSACTION_ENTRY);
	}
}
