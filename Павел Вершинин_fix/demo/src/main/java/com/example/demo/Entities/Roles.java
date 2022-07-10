package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "\"Roles\"", schema = "public")
public class Roles {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"Namerole\"")
    private String namerole;

    @JsonManagedReference("roles_staff")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roles_staff")
    private List<Staffs> staffs;

    @JsonManagedReference("roles_client")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roles_client")
    private List<Clients> clients;

    public Roles(String namerole, List<Staffs> staffs) {
        super();
        this.namerole = namerole;
        this.staffs = staffs;
    }

    public Roles() {

    }

    public List<Staffs> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staffs> staffs) {
        this.staffs = staffs;
    }

    public List<Clients> getClients() {
        return clients;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(id, roles.id) && Objects.equals(namerole, roles.namerole) && Objects.equals(staffs, roles.staffs) && Objects.equals(clients, roles.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namerole, staffs, clients);
    }
}
