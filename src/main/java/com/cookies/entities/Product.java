package com.cookies.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products", schema = "cookies")
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String image;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<Recipe> recipes;

    public Product(){}

    public Product(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public List<Recipe> getRecipes(){
        return recipes;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return id.hashCode() + name.hashCode() + image.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Product product = (Product) obj;
        return id.equals(product.id)
                &&name.equals(product.name)
                &&image.equals(product.image);
    }
}