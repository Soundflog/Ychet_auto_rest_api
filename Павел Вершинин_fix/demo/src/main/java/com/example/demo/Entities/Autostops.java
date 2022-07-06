package com.example.demo.Entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "\"Autostops\"")
public class Autostops {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"Name\"")
    private String name;
    @Column(name = "\"Address\"")
    private String address;
    @Column(name = "\"Seats\"")
    private Integer seats;
    @Column(name = "\"Type\"")
    private String type;

    @OneToMany(mappedBy = "autostops")
    private Set<Contracts> contracts;

    public Autostops(String name) {
        this.name = name;
    }

    public Autostops() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Autostops{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", seats=" + seats +
                ", type='" + type + '\'' +
                '}';
    }
}
