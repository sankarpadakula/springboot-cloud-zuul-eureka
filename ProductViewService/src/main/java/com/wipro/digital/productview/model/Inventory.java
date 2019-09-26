/**
 * 
 */
package com.wipro.digital.productview.model;

import javax.validation.constraints.Size;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Inventory {

    private Long id;
    
    @Size(max = 100)
    private String name;
    @Size(max = 500)
    private String description;
    private Double price;
    private Integer count;
   
}
