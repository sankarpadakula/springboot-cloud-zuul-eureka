/**
 * 
 */
package com.wipro.promotion.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.promotion.dao.PromotionRepository;
import com.wipro.promotion.model.Promotion;

/**
 * @author SPadakula
 *
 */
@Service
public class PromotionService {

    @Autowired
    private PromotionRepository repo;

    public List<Promotion> getAll(Long productId) {
        if (productId == null) {
        List<Promotion> list = repo.findAll();
        if (list == null || list.isEmpty())
            return productDetails();
        else
            return list;
        } else {
            return repo.findByProductId(productId);
        }
    }

    public Promotion save(Promotion inventory) {
        return repo.save(inventory);
    }

    public List<Promotion> save(List<Promotion> inventories) {
        return repo.saveAll(inventories);
    }
    
    public List<Promotion> getByProductId(Long id) {
        return repo.findByProductId(id);
    }

    public Optional<Promotion> getById(Long id) {
        Optional<Promotion> productPrice = repo.findById(id);
        return productPrice;
    }

    public void deleteByProductId(Long id) {
        repo.deleteByProductId(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private List<Promotion> productDetails() {
        List<Promotion> productList = new ArrayList<Promotion>();
        productList.add(
                Promotion.builder().productId(12828l).code("AQ1123").beginDate(new Date()).description("New year offer")
                .expireDate(Date.from(LocalDate.now().plusDays(30).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .build());
        repo.saveAll(productList);
        return productList;
    }

}
