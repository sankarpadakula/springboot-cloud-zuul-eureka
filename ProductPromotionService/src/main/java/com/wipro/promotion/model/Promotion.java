/**
 * 
 */
package com.wipro.promotion.model;

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
@Table(name = "promotion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long productId;
	private String code;
    private String type;
    private Integer priority;
    private String description;
    private Date beginDate;
    private Date expireDate;
	private String createdBy;
	private String modifiedBy;
	private Date modifiedDate;
	private Date createdDate;
	
}
