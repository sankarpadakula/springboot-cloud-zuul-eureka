package com.wipro.digital.productview.model;

import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Product {

    private Integer id;

    private String brand;

    private String name;

    private String model;
    
    private String mfDate;

    private Inventory inventory;
    private Price price;
    private List<Promotion> promotions;
    private String createdBy;
    private String modifiedBy;
    private Date modifiedDate;
    private Date createdDate;
}
