package com.example.pet2;

public class ThuCung {
    private int ID;
    private String TenTT,GiongLoai,TrongLuong,MauLong;

    private byte[] Hinh;
    public ThuCung(int ID, String tenTT, String giongLoai, String mauLong, String trongLuong, byte[] hinh) {
        this.ID = ID;
        TenTT = tenTT;
        GiongLoai = giongLoai;
        MauLong = mauLong;
        TrongLuong = trongLuong;
        Hinh = hinh;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenTT() {
        return TenTT;
    }

    public void setTenTT(String tenTT) {
        TenTT = tenTT;
    }

    public String getGiongLoai() {
        return GiongLoai;
    }

    public void setGiongLoai(String giongLoai) {
        GiongLoai = giongLoai;
    }

    public String getMauLong() {
        return MauLong;
    }

    public void setMauLong(String mauLong) {
        MauLong = mauLong;
    }

    public String getTrongLuong() {
        return TrongLuong;
    }

    public void setTrongLuong(String trongLuong) {
        TrongLuong = trongLuong;
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }
}
