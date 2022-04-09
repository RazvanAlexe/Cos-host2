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
    private int id;
    private String title;
    private double price;
    private String description;
    private int authorid;
    private double rating;
    private int categoryid;
    private int unitssold;
    private int availableunits;
}
