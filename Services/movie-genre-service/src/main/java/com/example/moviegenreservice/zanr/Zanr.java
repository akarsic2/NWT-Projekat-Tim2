package com.example.moviegenreservice.zanr;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.moviegenreservice.film.Film;

@Entity
@Table (name = "zanr")
public class Zanr {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String zanr;

    @ManyToMany(mappedBy = "zanrs")
    private Set<Film> movies;

    public Zanr() {}

    public Zanr(String zanr) {
        this.zanr = zanr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    
}
