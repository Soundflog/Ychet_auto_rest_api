package com.example.demo.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"Autos\"")
public class Autos {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"Autoname\"")
    private String autoname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "autos")
    private List<Autos> autos;

    public Autos() {
    }

    public List<Autos> getAutos() {
        return autos;
    }

    public void setAutos(List<Autos> autos) {
        this.autos = autos;
    }

    public Autos(String autoname) {
        super();
        this.autoname = autoname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutoname() {
        return autoname;
    }

    public void setAutoname(String autoname) {
        this.autoname = autoname;
    }

    @Override
    public String toString() {
        return "Autos{" +
                "id=" + id +
                ", autoname='" + autoname + '\'' +
                ", autos=" + autos +
                '}';
    }
}
