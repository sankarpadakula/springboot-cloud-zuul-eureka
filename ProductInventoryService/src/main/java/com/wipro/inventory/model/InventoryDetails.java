/**
 * 
 */
package com.wipro.inventory.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author SPadakula
 *
 */
@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InventoryDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long productId;
	private String name;
	private String description;
	private Double price;
	private Integer count;
	/*private String color;
	private String productType;*/
	private String createdBy;
	private String modifiedBy;
	private Date modifiedDate;
	private Date createdDate;
	
}
