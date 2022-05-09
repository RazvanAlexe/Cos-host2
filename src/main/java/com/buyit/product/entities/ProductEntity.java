package com.buyit.product.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    private Integer id;
    private String title;
    private double price;
    private String description;
    private Integer authorid;
    private double rating;
    private Integer categoryid;
    private Integer unitssold;
    private Integer availableunits;
}
