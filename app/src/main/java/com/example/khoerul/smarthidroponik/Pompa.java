package com.example.khoerul.smarthidroponik;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Pompa implements Parcelable {

    public String getPomnutrisiA() {
        return pomnutrisiA;
    }

    public void setPomnutrisiA(String pomnutrisiA) {
        this.pomnutrisiA = pomnutrisiA;
    }

    public String getPomnutrisiB() {
        return pomnutrisiB;
    }

    public void setPomnutrisiB(String pomnutrisiB) {
        this.pomnutrisiB = pomnutrisiB;
    }

    public String getPomairBasa() {
        return pomairBasa;
    }

    public void setPomairBasa(String pomairBasa) {
        this.pomairBasa = pomairBasa;
    }

    String pomnutrisiA;
    String pomnutrisiB;
    String pomairBasa;

    public Pompa() {

    }



    public Pompa (JSONObject object){
        try {
            String pomnutrisiA = object.getString("pomnutrisiA");
            String pomnutrisiB = object.getString("pomnutrisiB");
            String  pomairBasa =object.getString("pomairBasa");

            this.pomnutrisiA = pomnutrisiA;
            this.pomnutrisiB = pomnutrisiB;
            this.pomairBasa = pomairBasa ;
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    protected Pompa (Parcel in) {
        pomnutrisiA = in.readString();
        pomnutrisiB = in.readString();
        pomairBasa = in.readString();
    }

    public static final Creator<Pompa> CREATOR = new Creator<Pompa>() {
        @Override
        public Pompa createFromParcel(Parcel in) {
            return new Pompa(in);
        }

        @Override
        public Pompa[] newArray(int size) {
            return new Pompa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pomnutrisiA);
        dest.writeString(pomnutrisiB);
        dest.writeString(pomairBasa);

    }
}