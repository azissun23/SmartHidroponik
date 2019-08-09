package com.example.khoerul.smarthidroponik;

import org.json.JSONException;
import org.json.JSONObject;

public class Sensor {
    private String nutrisi = "nutrisi";
    private String volair = "volair";
    private String pH = "pH";

    public String getNutrisi() {
        return nutrisi;
    }

    public void setNutrisi(String nutrisi) {
        this.nutrisi = nutrisi;
    }

    public String getVolair() {
        return volair;
    }

    public void setVolair(String volair) {
        this.volair = volair;
    }

    public String getpH() {
        return pH;
    }

    public void setpH (String pH) {
        this.pH = pH;
    }

    public Sensor(JSONObject obj) {
        try {
            String pH = obj.getString("pH");
            String nutrisi = obj.getString("nutrisi");
            String volair = obj.getString("volair");

            this.nutrisi = nutrisi;
            this.pH = pH;
            this.volair = volair;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}