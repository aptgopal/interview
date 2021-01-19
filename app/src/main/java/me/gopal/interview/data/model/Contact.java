package me.gopal.interview.data.model;

import com.google.gson.annotations.SerializedName;

public class Contact {

    public final long id;
    public final String firstName;
    public final String phone;
    public final String longitude;
    public final String favouriteColor;
    public final String email;
    public final String jobTitle;
    public final String createdAt;
    public final String latitude;
    public final String lastName;
    public final String avatar;

    public Contact(long id, String firstName, String phone, String longitude, String favouriteColor, String email, String jobTitle, String createdAt, String latitude, String lastName, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.phone=phone;
        this.longitude=longitude;
        this.favouriteColor=favouriteColor;
        this.email=email;
        this.jobTitle=jobTitle;
        this.createdAt=createdAt;
        this.latitude=latitude;
        this.lastName=lastName;
        this.avatar=avatar;
    }
}