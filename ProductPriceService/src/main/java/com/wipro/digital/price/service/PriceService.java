/**
 * 
 */
package com.wipro.digital.price.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.digital.price.dao.PriceRepository;
import com.wipro.digital.price.model.PriceDetails;

/**
 * @author USET
 *
 */
@Service
public class PriceService {
	@Autowired
	private PriceRepository repo;
	
	/**
	 * 
	 * @return Account
	 */
    public List<PriceDetails> saveAccounts() {
    	productDetails();
        return (List<PriceDetails>) repo.findAll();
    }
    
    /**
     * 
     * @param id - id
     * @return Account
     */
    public Optional<PriceDetails> getProductPriceById(Long id) {
    	Optional<PriceDetails> productPrice =  repo.findById(id);
    	return productPrice;
    }
    
    /**
     * 
     * @param id - id
     */
    public void delete(Long id) {
    	repo.deleteById(id);
    }
    
	/**
	 * 
	 * @return Account
	 */
	private List<PriceDetails> productDetails() {
		List<PriceDetails> productList = new ArrayList<PriceDetails>();
		PriceDetails product1 = new PriceDetails("Laptop", 12828l);
		PriceDetails product2 = new PriceDetails("Mobile", 12828l);
		PriceDetails product3 = new PriceDetails("Camera", 12828l);
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		repo.saveAll(productList);
		return productList;
	}
	 
}
