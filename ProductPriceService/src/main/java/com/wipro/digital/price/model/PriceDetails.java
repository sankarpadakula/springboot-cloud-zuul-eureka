/**
 * 
 */
package com.wipro.digital.price.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zamil
 *
 */
@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PriceDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private String productName;
	private Long price;
	/**
	 * @param name
	 * @param balance
	 */
	public PriceDetails(String name, Long balance) {
		super();
		this.productName = name;
		this.price = balance;
	}
	
	
}
