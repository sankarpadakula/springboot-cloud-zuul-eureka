/**
 * 
 */
package com.wipro.product.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.product.model.Product;

/**
 * @author SPadakula
 *
 */
@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

}
