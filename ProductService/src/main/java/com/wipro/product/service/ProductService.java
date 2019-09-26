/**
 * 
 */
package com.wipro.product.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.product.dao.ProductRepository;
import com.wipro.product.model.Product;

/**
 * @author SPadakula
 *
 */
@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repo;

    public List<Product> getAll(Long productId) {
        if (productId == null) {
        List<Product> list = repo.findAll();
        if (list == null || list.isEmpty())
            return productDetails();
        else
            return list;
        } else {
            return Arrays.asList(repo.findById(productId).get());
        }
    }

    public Product save(Product inventory) {
        return repo.save(inventory);
    }

    public Product getByProductId(Long id) {
        return repo.findById(id).get();
    }

    public Optional<Product> getById(Long id) {
        Optional<Product> productPrice = repo.findById(id);
        return productPrice;
    }

    public void deleteByProductId(Long id) {
        repo.deleteById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private List<Product> productDetails() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(Product.builder().id(12828l).name("shoe").brand("Adidas").mfDate(new Date())
                .model(2019).build());
        repo.saveAll(productList);
        return productList;
    }

}
