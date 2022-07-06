package com.example.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"Authorizations\"", schema = "public")
public
class Authorizations {
    @Id
    @Column(name = "\"Id\"")
    private Long id;
    @Column(name = "\"Login\"")
    private String login;
    @Column(name = "\"Password\"")
    private String password;


    public Authorizations() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = Id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Authorizations{" +
                "Id=" + id +
                ", Login='" + login + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}
