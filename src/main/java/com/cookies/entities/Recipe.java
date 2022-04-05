package com.cookies.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "recipes", schema = "cookies")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String video;

    @Column
    private String photo;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_product"
            , schema = "cookies"
            , joinColumns = @JoinColumn(name = "recipe_id")
            , inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Recipe() {
    }

    public Recipe(String name, String description, String video, String photo, LocalDate date) {
        this.name = name;
        this.description = description;
        this.video = video;
        this.photo = photo;
        this.date = date;
    }

    public List<Product> getProducts(){
        return products;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", video='" + video + '\'' +
                ", photo='" + photo + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public int hashCode() {
        return id.hashCode() + name.hashCode() + description.hashCode()
                + video.hashCode() + photo.hashCode() + date.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Recipe recipe = (Recipe) obj;
        return id.equals(recipe.id)
                &&name.equals(recipe.name)
                &&description.equals(recipe.description)
                &&video.equals(recipe.video)
                &&photo.equals(recipe.photo)
                &&date.equals(recipe.date);
    }
}