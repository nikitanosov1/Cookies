package com.cookies.entities;

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
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
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
}