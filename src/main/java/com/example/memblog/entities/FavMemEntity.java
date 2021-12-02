package com.example.memblog.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;


//todo возможно не нужен геттер на user ибо не безопасно и user_id не должен быть null
@Entity
public class FavMemEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "liked_memes",
            joinColumns = @JoinColumn(name = "FavMem_id"),
            inverseJoinColumns = @JoinColumn(name = "mem_id")
    )
    private Set<MemEntity> favMemes = new HashSet<>();

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void saveMeme(MemEntity mem){
        this.favMemes.add(mem);
    }

    public void deleteMeme(MemEntity meme){this.favMemes.remove(meme);}

    public void deleteAll(){this.favMemes.clear();}

    public Set<MemEntity> getFavMemes() {return favMemes;}

    public UserEntity getUser() {return user;}

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setFavMemes(Set<MemEntity> favMemes) {
        this.favMemes = favMemes;
    }

    public FavMemEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
