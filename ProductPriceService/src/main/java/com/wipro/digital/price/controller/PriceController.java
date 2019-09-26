/**
 * 
 */
package com.wipro.digital.price.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.digital.price.model.PriceDetails;
import com.wipro.digital.price.service.PriceService;


/**
 * @author USET
 *
 */
@RestController
public class PriceController {
	
	@Autowired
	private PriceService service;

	@GetMapping("/saveprices")
	public List<PriceDetails> getAccounts() {
		List<PriceDetails> accounts = service.saveAccounts();
		return accounts;
	}
	
	@GetMapping("/getprice/{id}")
	public Optional<PriceDetails> findAccountById(@PathVariable(required = true) Long id) {
		Optional<PriceDetails> price = service.getProductPriceById(id);
		return price;
	}
}
