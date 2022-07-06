package com.example.demo.Entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "\"Discounts\"", schema = "public")
public class Discounts {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"Sale\"")
    private Integer sale;

    @OneToMany(mappedBy = "discounts")
    List<Vouchers> vouchers;

    public Discounts(Integer sale) {
        super();
        this.sale = sale;
    }

    public Discounts() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Discounts{" +
                "id=" + id +
                ", sale=" + sale + '\'' +
                '}';
    }
}
