package com.example.demo.Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "\"Contracts\"")
public class Contracts {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"ConclusionDate\"")
    private Timestamp conclusionDate;

    @ManyToOne
    @JoinColumn(name = "\"AutostopsId\"")
    private Autostops autostops;

    @ManyToOne
    @JoinColumn(name = "\"StaffsId\"")
    private Staffs staffs;

    @ManyToOne
    @JoinColumn(name = "\"VouchersId\"")
    private Vouchers vouchers;

    @ManyToOne
    @JoinColumn(name = "\"ClientsId\"")
    private Clients clients;

    public Contracts(Timestamp conclusionDate) {
        super();
        this.conclusionDate = conclusionDate;
    }

    public Contracts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(Timestamp conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public Autostops getAutostops() {
        return autostops;
    }

    public void setAutostops(Autostops autostops) {
        this.autostops = autostops;
    }

    public Staffs getStaffs() {
        return staffs;
    }

    public void setStaffs(Staffs staffs) {
        this.staffs = staffs;
    }

    public Vouchers getVouchers() {
        return vouchers;
    }

    public void setVouchers(Vouchers vouchers) {
        this.vouchers = vouchers;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Contracts{" +
                "id=" + id +
                ", conclusionDate=" + conclusionDate +
                ", autostops=" + autostops +
                ", staffs=" + staffs +
                ", vouchers=" + vouchers +
                ", clients=" + clients +
                '}';
    }
}
