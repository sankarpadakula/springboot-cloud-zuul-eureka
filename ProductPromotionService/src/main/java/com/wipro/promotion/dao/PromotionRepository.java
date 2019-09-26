/**
 * 
 */
package com.wipro.promotion.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.promotion.model.Promotion;

/**
 * @author SPadakula
 *
 */
@Repository
@Transactional
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    public List<Promotion> findByProductId(Long productId);

    public void deleteByProductId(Long productId);

}
