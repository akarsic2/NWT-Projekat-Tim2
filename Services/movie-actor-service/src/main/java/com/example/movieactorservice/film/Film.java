package com.example.movieactorservice.film;

import java.util.HashSet;
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
import javax.validation.constraints.Pattern;

import com.example.movieactorservice.glumac.Glumac;
import com.sun.istack.NotNull;

@Entity
@Table (name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String film;

    @ManyToMany
    @JoinTable(
        name = "glumac_film", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "glumac_id"))
    private Set<Glumac> glumci = new HashSet<>();

    public Film() {}

    public Film(String film) {
        this.film = film;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public void setGlumci(Glumac glumac) {
        this.glumci.add(glumac);
    }
    
}
