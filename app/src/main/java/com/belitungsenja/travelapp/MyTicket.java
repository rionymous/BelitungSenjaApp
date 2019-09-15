/*
 * Created by Android Rion on 9/15/19 10:28 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/14/19 2:41 PM
 * Kunjungi androidrion.com untuk tutorial Android Studio
 */

package com.belitungsenja.travelapp;

public class MyTicket {

    String nama_paket, durasi, jumlah_tamu;

    public MyTicket() {
    }

    public MyTicket(String nama_paket, String durasi, String jumlah_tamu) {
        this.nama_paket = nama_paket;
        this.durasi = durasi;
        this.jumlah_tamu = jumlah_tamu;
    }

    public String getNama_paket() {
        return nama_paket;
    }

    public void setNama_paket(String nama_paket) {
        this.nama_paket = nama_paket;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getJumlah_tamu() {
        return jumlah_tamu;
    }

    public void setJumlah_tamu(String jumlah_tamu) {
        this.jumlah_tamu = jumlah_tamu;
    }



}
