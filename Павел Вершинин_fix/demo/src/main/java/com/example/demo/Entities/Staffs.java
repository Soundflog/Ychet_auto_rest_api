package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "\"Staffs\"", schema = "public")
public class Staffs {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"Lastname\"")
    private String lastname;
    @Column(name = "\"Firstname\"")
    private String firstname;
    @Column(name = "\"Midname\"")
    private String midname;
    @Column(name = "\"Desription\"")
    private String desription;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "\"RolesId\"")
    private Roles roles_staff;

    @JsonManagedReference
    @OneToMany(mappedBy = "staffs")
    private Set<Contracts> contracts;

    public Staffs(Long id) {
        super();
        this.id = id;
    }

    public Staffs() {

    }

    public Roles getRoles_staff() {
        return roles_staff;
    }

    public void setRoles_staff(Roles roles_staff) {
        this.roles_staff = roles_staff;
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

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    @Override
    public String toString() {
        return "Staffs{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", midname='" + midname + '\'' +
                ", desription='" + desription + '\'' +
                ", roles_staff=" + roles_staff +
                '}';
    }
}
