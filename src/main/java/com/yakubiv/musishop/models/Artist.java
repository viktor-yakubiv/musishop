package com.yakubiv.musishop.models;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    public Artist() {}

    public Artist(String name) {
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
