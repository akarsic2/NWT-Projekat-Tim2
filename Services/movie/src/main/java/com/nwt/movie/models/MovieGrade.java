package com.nwt.movie.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MovieGrade{
    private @Id Long id;
    private @ManyToOne Movie movie;
    private @ManyToOne Grade grade;
    private @Basic Integer korisnikId;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    // public Movie getMovie(){
    //     return this.movie;
    // }

    // public void setMovie(Movie m){
    //     this.movie = m;
    // }

    public Grade getGrade(){
        return this.grade;
    }

    public void setGrade(Grade g){
        this.grade = g;
    }

    public Integer getKorisnikId(){
        return this.korisnikId;
    }

    public void setKorisnikId(Integer korisnikId){
        this.korisnikId = korisnikId;
    }
} 