package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "\"Vouchers\"", schema = "public")
public class Vouchers {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"Indate\"")
    private Timestamp indate;
    @Column(name = "\"Outdate\"")
    private Timestamp outdate;
    @Column(name = "\"Summ\"")
    private Integer summ;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "\"DiscountsId\"")
    Discounts discounts;

    @JsonManagedReference
    @OneToMany(mappedBy = "vouchers")
    private Set<Contracts> contracts;

    public Discounts getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Discounts discounts) {
        this.discounts = discounts;
    }

    public Set<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contracts> contracts) {
        this.contracts = contracts;
    }

    public Vouchers(Integer summ) {
        super();
        this.summ = summ;
    }

    public Vouchers() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getIndate() {
        return indate;
    }

    public void setIndate(Timestamp indate) {
        this.indate = indate;
    }

    public Timestamp getOutdate() {
        return outdate;
    }

    public void setOutdate(Timestamp outdate) {
        this.outdate = outdate;
    }

    public Integer getSumm() {
        return summ;
    }

    public void setSumm(Integer summ) {
        this.summ = summ;
    }


    @Override
    public String toString() {
        return "Vouchers{" +
                "id=" + id +
                ", indate=" + indate +
                ", outdate=" + outdate +
                ", summ=" + summ +
                ", discounts=" + discounts +
                '}';
    }

}
