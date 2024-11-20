package com.example.java_gyak_beadando.Lotto_Query;

import java.util.List;

public class LottoDto {
    private int ev;
    private int het;
    private String szamok; // A lottószámok
    private List<NyeremenyDto> nyeremenyek; // A nyeremények részletei

    // Getters és Setters
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

    public List<NyeremenyDto> getNyeremenyek() {
        return nyeremenyek;
    }

    public void setNyeremenyek(List<NyeremenyDto> nyeremenyek) {
        this.nyeremenyek = nyeremenyek;
    }
}

class NyeremenyDto {
    private int talalat;
    private int darab;
    private int ertek;

    // Getters és Setters
    public int getTalalat() {
        return talalat;
    }

    public void setTalalat(int talalat) {
        this.talalat = talalat;
    }

    public int getDarab() {
        return darab;
    }

    public void setDarab(int darab) {
        this.darab = darab;
    }

    public int getErtek() {
        return ertek;
    }

    public void setErtek(int ertek) {
        this.ertek = ertek;
    }
}
