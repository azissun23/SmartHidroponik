package com.example.khoerul.smarthidroponik.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements Parcelable {

    String IdUser;
    String password;
    String email;

    public User() {

    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(JSONObject object){
        try {
            String id = object.getString("id");
            String email = object.getString("email");
            String  password =object.getString("password");

            this.IdUser = id;
            this.email = email;
            this.password = password ;
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    protected User(Parcel in) {
        IdUser = in.readString();
        password = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(IdUser);
        dest.writeString(password);
        dest.writeString(email);

    }
}