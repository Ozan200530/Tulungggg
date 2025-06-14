package com.example.login;

public class Laptop {
    private String id, namaLaptop , hargaLaptop , stok ;

    public Laptop(String id, String namaLaptop, String hargaLaptop, String stok) {
        this.id = id;
        this.namaLaptop = namaLaptop;
        this.hargaLaptop = hargaLaptop;
        this.stok = stok;
    }

    public Laptop() {
    }

    public String getStok() {
        return stok;
    }

    public String getHargaLaptop() {
        return hargaLaptop;
    }

    public String getNamaLaptop() {
        return namaLaptop;
    }

    public String getId() {
        return id;
    }
}
