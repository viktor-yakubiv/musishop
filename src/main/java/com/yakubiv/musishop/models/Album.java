package com.yakubiv.musishop.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "albums")
public class Album {
    @Id @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public Album() {}

    public Album(Artist artist, String name) {
        this(artist, name, Calendar.getInstance().get(Calendar.YEAR) + 1900);
    }

    public Album(Artist artist, String name, Integer releaseYear) {
        this.artist = artist;
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }
}
