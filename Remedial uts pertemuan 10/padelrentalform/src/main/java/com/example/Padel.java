package com.example;

public class Padel {
    private int id;
    private String nama;
    private String noHP;
    private String tanggal;
    private String jamMulai;
    private String jamSelesai;
    private String lapangan;
    
    public Padel(int id, String nama, String noHP, String tanggal, String jamMulai, String jamSelesai, String lapangan){
        this.id = id;
        this.nama = nama;
        this.noHP = noHP;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.lapangan = lapangan;
    }
    
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNoHP() { return noHP; }
    public String getTanggal() { return tanggal; }
    public String getJamMulai() { return jamMulai; }
    public String getJamSelesai() { return jamSelesai; }
    public String getLapangan() { return lapangan; }
}