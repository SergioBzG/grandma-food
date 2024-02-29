package com.restaurant.grandmasfood.entity;

import java.util.Date;

public class OrderEntity {

    private Long id;
    private Integer cantidad;
    private String additionalInfo;
    private Double subTotal;
    private Double iva;
    private Double total;
    private Boolean ordered;
    private Date dateOrdered;
    private Date dateOrder;
}
