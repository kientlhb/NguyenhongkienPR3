package com.nguyenhongkien.fruitstore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String description;

    private int stock;           // Tồn kho
    private String mainImageUrl; // Ảnh chính

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "fruit", cascade = CascadeType.ALL)
    private List<Product> images;

    @OneToMany(mappedBy = "fruit")
    private List<Review> reviews;

    public void setId(Long id) {
        this.id = id;
    }
}
