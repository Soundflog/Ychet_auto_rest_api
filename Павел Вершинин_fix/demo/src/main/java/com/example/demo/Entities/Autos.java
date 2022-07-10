package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonManagedReference("auto_cl")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "autos_cl")
    private List<Clients> autos_cl;

    public Autos() {
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

    public List<Clients> getAutos_cl() {
        return autos_cl;
    }

    public void setAutos_cl(List<Clients> autos_cl) {
        this.autos_cl = autos_cl;
    }

    public void setAutoname(String autoname) {
        this.autoname = autoname;
    }

    @Override
    public String toString() {
        return "Autos{" +
                "id=" + id +
                ", autoname='" + autoname + '\'' +
                '}';
    }
}
