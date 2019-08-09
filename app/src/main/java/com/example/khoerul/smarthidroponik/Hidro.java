package com.example.khoerul.smarthidroponik;

import org.json.JSONObject;

public class Hidro {

    private String id = "id";
    private String namatanaman = "namatanaman";
    private String usiapanen = "usiapanen";
    private String pH_Air = "pH_Air";
    private String minggu1 = "minggu1";
    private String minggu2 = "minggu2";
    private String minggu3 = "minggu3";
    private String minggu4 = "minggu4";
    private String minggu6_8 = "minggu6_8";
    private String ppm_maksimum = "ppm_maksimum";
    private String gambar = "gambar";
    private String ket_panen = "ket_panen";
    private String caraisinutrisi = "caraisinutrisi";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamatanaman() {
        return namatanaman;
    }

    public void setNamatanaman(String namatanaman) {
        this.namatanaman = namatanaman;
    }

    public String getUsiapanen() {
        return usiapanen;
    }

    public void setUsiapanen(String usiapanen) {
        this.usiapanen = usiapanen;
    }

    public String getpH_Air() {
        return pH_Air;
    }

    public void setpH_Air(String pH_Air) {
        this.pH_Air = pH_Air;
    }

    public String getMinggu1() {
        return minggu1;
    }

    public void setMinggu1(String minggu1) {
        this.minggu1 = minggu1;
    }

    public String getMinggu2() {
        return minggu2;
    }

    public void setMinggu2(String minggu2) {
        this.minggu2 = minggu2;
    }

    public String getMinggu3() {
        return minggu3;
    }

    public void setMinggu3(String minggu3) {
        this.minggu3 = minggu3;
    }

    public String getMinggu4() {
        return minggu4;
    }

    public void setMinggu4(String minggu4) {
        this.minggu4 = minggu4;
    }

    public String getMinggu6_8() {
        return minggu6_8;
    }

    public void setMinggu6_8(String minggu6_8) {
        this.minggu6_8 = minggu6_8;
    }

    public String getPpm_maksimum() { return ppm_maksimum; }

    public void setPpm_maksimum(String ppm_maksimum) {
        this.ppm_maksimum = ppm_maksimum;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKet_panen() {
        return ket_panen;
    }

    public void setKet_panen(String ket_panen) {
        this.ket_panen = ket_panen;
    }

    public String getCaraisinutrisi() {
        return caraisinutrisi;
    }

    public void setCaraisinutrisi(String caraisinutrisi) {
        this.caraisinutrisi = caraisinutrisi;
    }

    Hidro(JSONObject obj) {
        try {
            String id = obj.getString("id");
            String namatanaman = obj.getString("namatanaman");
            String usiapanen = obj.getString("usiapanen");
            String pH_Air = obj.getString("pH_Air");
            String minggu1 = obj.getString("minggu1");
            String minggu2 = obj.getString("minggu2");
            String minggu3 = obj.getString("minggu3");
            String minggu4 = obj.getString("minggu4");
            String minggu6_8 = obj.getString("minggu6_8");
            String ppm_maksimum = obj.getString("ppm_maksimum");
            String gambar = obj.getString("gambar");
            String ket_panen = obj.getString("ket_panen");
            String caraisinutrisi = obj.getString("caraisinutrisi");

            this.id = id;
            this.namatanaman = namatanaman;
            this.usiapanen = usiapanen;
            this.pH_Air = pH_Air;
            this.minggu1 = minggu1;
            this.minggu2 = minggu2;
            this.minggu3 = minggu3;
            this.minggu4 = minggu4;
            this.minggu6_8 = minggu6_8;
            this.ppm_maksimum = ppm_maksimum;
            this.gambar = gambar;
            this.ket_panen = ket_panen;
            this.caraisinutrisi = caraisinutrisi;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
