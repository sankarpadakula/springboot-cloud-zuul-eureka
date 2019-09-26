/**
 * 
 */
package com.wipro.inventory.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.inventory.model.InventoryDetails;

/**
 * @author SPadakula
 *
 */
@Repository
@Transactional
public interface InventoryRepository extends JpaRepository<InventoryDetails, Long> {

    public List<InventoryDetails> findByProductId(Long productId);

    public void deleteByProductId(Long productId);

}
