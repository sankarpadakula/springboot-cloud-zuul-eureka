/**
 * 
 */
package com.wipro.digital.price.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.digital.price.model.PriceDetails;

/**
 * @author USET
 *
 */
@Repository
@Transactional
public interface PriceRepository extends JpaRepository<PriceDetails, Long> {

}
