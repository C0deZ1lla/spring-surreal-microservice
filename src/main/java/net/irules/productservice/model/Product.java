package net.irules.productservice.model;


import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
