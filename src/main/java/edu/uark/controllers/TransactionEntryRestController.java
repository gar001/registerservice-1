package edu.uark.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.commands.transaction.TransactionQuery;
import edu.uark.commands.transaction.TransactionEntryCountsQuery;
import edu.uark.commands.transaction.TransactionSaveCommand;
import edu.uark.commands.transaction.TransactionEntrySaveCommand;
import edu.uark.models.api.Transaction;

@RestController
@RequestMapping(value = "/transactionEntry")
public class EmployeeRestController {
	@RequestMapping(value = "/apiv0/{transactionEntryId}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable UUID transactionId) {
		return (new transactionQuery()).
			setTransactionId(transactionId).
			execute();
	}

	@RequestMapping(value = "/apiv0/removeproduct", method = RequestMethod.PUT)
	public TransactionEntry addProduct(@RequestBody RemoveProduct removeProduct) {
		return (new TransactionEntryAddProductCommand()).
			setApiTransactionEntry(transactionEntry).
			execute();
	}

	@RequestMapping(value = "/apiv0/addproduct", method = RequestMethod.PUT)
	public TransactionEntry removeProduct(@RequestBody AddProduct addProduct) {
		return (new TransactionEntryRemoveProductCommand()).
			setApiTransactionEntry(transactionEntry).
			execute();
	}

	@RequestMapping(value = "/apiv0/", method = RequestMethod.PUT)
	public TransactionEntry createTransaction(@RequestBody TransactionEntry transactionEntry) {
		return (new TransactionEntryCreateTransactionCommand()).
			setApiTransactionEntry(transactionEntry).
			execute();
	}

	@RequestMapping(value = "/apiv0/", method = RequestMethod.PUT)
	public TransactionEntry putTransactionEntry(@RequestBody TransactionEntry transactionEntry) {
		return (new TransactionEntrySaveCommand()).
			setApiTransactionEntry(transactionEntry).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (EmployeeRestController)";
	}
}
