package com.example.demo.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "\"Clients\"")
public class Clients {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"Lastname\"")
    private String lastname;
    @Column(name = "\"Firstname\"")
    private String firstname;
    @Column(name = "\"Midname\"")
    private String midname;
    @Column(name = "\"Gosnumber\"")
    private String gosnumber;

    @ManyToOne
    @JoinColumn(name = "\"AutosId\"")
    private Autos autos;

    @Column(name = "\"Numberphone\"")
    private String numberphone;
    @Column(name = "\"SeriaNumberDoc\"")
    private String seriaNumberDoc;

    @ManyToOne
    @JoinColumn(name = "\"RolesId\"")
    private Roles roles_client;

    @OneToMany(mappedBy = "clients")
    private Set<Contracts> contracts;

    public Clients() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public String getGosnumber() {
        return gosnumber;
    }

    public void setGosnumber(String gosnumber) {
        this.gosnumber = gosnumber;
    }

    public Autos getAutos() {
        return autos;
    }

    public void setAutos(Autos autos) {
        this.autos = autos;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getSeriaNumberDoc() {
        return seriaNumberDoc;
    }

    public void setSeriaNumberDoc(String seriaNumberDoc) {
        this.seriaNumberDoc = seriaNumberDoc;
    }

    public Roles getRoles_client() {
        return roles_client;
    }

    public void setRoles_client(Roles roles_client) {
        this.roles_client = roles_client;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", midname='" + midname + '\'' +
                ", gosnumber='" + gosnumber + '\'' +
                ", autos=" + autos +
                ", numberphone='" + numberphone + '\'' +
                ", seriaNumberDoc='" + seriaNumberDoc + '\'' +
                ", roles_client=" + roles_client +
                '}';
    }
}
