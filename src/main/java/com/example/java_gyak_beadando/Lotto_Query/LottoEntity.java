package com.example.java_gyak_beadando.Lotto_Query;

import jakarta.persistence.*;

@Entity
@Table(name = "huzas")
public class LottoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ev")
    private int ev;

    @Column(name = "het")
    private int het;

    @Column(name = "szamok")
    private String szamok;

    // Getters és Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEv() {
        return ev;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public int getHet() {
        return het;
    }

    public void setHet(int het) {
        this.het = het;
    }

    public String getSzamok() {
        return szamok;
    }

    public void setSzamok(String szamok) {
        this.szamok = szamok;
    }
}
