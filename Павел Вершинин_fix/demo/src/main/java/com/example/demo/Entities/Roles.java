package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"Roles\"", schema = "public")
public class Roles {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"Namerole\"")
    private String namerole;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roles_staff")
    private List<Staffs> staffs;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roles_client")
    private List<Clients> clients;

    public Roles(String namerole, List<Staffs> staffs) {
        super();
        this.namerole = namerole;
        this.staffs = staffs;
    }

    public Roles() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamerole() {
        return namerole;
    }

    public void setNamerole(String namerole) {
        this.namerole = namerole;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", namerole='" + namerole + '\'' +
                '}';
    }

}
