package com.example.memblog.entities;

import javax.persistence.*;

import java.util.LinkedList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class MemEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String reference;
    private Integer width;
    private Integer length;

    @ManyToMany (mappedBy = "favMemes")
    private List<FavMemEntity> favour = new LinkedList<>();

    public List<FavMemEntity> getFavour() {
        return favour;
    }

    public void setFavour(List<FavMemEntity> favour) {
        this.favour = favour;
    }

    public MemEntity() {
    }

    public void saveFav(FavMemEntity fav){
        this.favour.add(fav);
    }

    public void deleteFrom(FavMemEntity fav){this.favour.remove(fav);}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
