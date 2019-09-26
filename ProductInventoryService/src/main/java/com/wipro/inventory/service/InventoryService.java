/**
 * 
 */
package com.wipro.inventory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.inventory.dao.InventoryRepository;
import com.wipro.inventory.model.InventoryDetails;

/**
 * @author SPadakula
 *
 */
@Service
public class InventoryService {
    
    @Autowired
    private InventoryRepository repo;

    public List<InventoryDetails> getAll() {
        List<InventoryDetails> list = repo.findAll();
        if (list == null || list.isEmpty())
            return productDetails();
        else
            return list;
    }

    public InventoryDetails save(InventoryDetails inventory) {
        return repo.save(inventory);
    }

    public List<InventoryDetails> getByProductId(Long id) {
        return repo.findByProductId(id);
    }

    public Optional<InventoryDetails> getById(Long id) {
        Optional<InventoryDetails> productPrice = repo.findById(id);
        return productPrice;
    }

    public void deleteByProductId(Long id) {
        repo.deleteByProductId(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private List<InventoryDetails> productDetails() {
        List<InventoryDetails> productList = new ArrayList<InventoryDetails>();
        productList.add(InventoryDetails.builder().productId(12828l).name("name").price(100d).count(100)
                .description("description").build());
        
     /*   productList.add(InventoryDetails.builder().productId(12828l).color("black").stock(100).size("10")
                .productType("Laptop").createdDate(new Date()).modifiedDate(new Date()).createdBy("Sankar")
                .modifiedBy("Sankar").build());
        productList.add(InventoryDetails.builder().productId(12828l).color("black").stock(100).size("10")
                .productType("Mobile").createdDate(new Date()).modifiedDate(new Date()).createdBy("Sankar")
                .modifiedBy("Sankar").build());
        productList.add(InventoryDetails.builder().productId(12828l).color("black").stock(100).size("10")
                .productType("Camera").createdDate(new Date()).modifiedDate(new Date()).createdBy("Sankar")
                .modifiedBy("Sankar").build());*/
        repo.saveAll(productList);
        return productList;
    }

}
