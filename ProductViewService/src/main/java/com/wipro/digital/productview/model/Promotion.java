package com.wipro.digital.productview.model;

import java.util.Date;

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
public class Promotion {

    private String code;
    private String type;
    private Integer priority;
    private String description;
    private Date beginDate;
    private Date expireDate;
}
