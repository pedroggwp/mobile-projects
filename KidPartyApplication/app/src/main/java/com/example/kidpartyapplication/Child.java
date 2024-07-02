package com.example.kidpartyapplication;

import java.util.Date;

public class Child {

    private long id = 0;
    private String name;
    private String responsable;
    private String phoneNumber;
    private Date bornDate;
    private boolean isRestricted;
    private String image;
    private float rank;

    public Child() {
    }

    public Child(String name) {
        this.name = name;
    }

//    public Child(String name, String responsable, String phoneNumber, Date bornDate, boolean isRestricted, String image, float rank) {
//        this.name = name;
//        this.responsable = responsable;
//        this.phoneNumber = phoneNumber;
//        this.bornDate = bornDate;
//        this.isRestricted = isRestricted;
//        this.image = image;
//        this.rank = rank;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getResponsable() {
//        return responsable;
//    }
//
//    public void setResponsable(String responsable) {
//        this.responsable = responsable;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public Date getBornDate() {
//        return bornDate;
//    }
//
//    public void setBornDate(Date bornDate) {
//        this.bornDate = bornDate;
//    }
//
//    public boolean isRestricted() {
//        return isRestricted;
//    }
//
//    public void setRestricted(boolean restricted) {
//        isRestricted = restricted;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public float getRank() {
//        return rank;
//    }
//
//    public void setRank(float rank) {
//        this.rank = rank;
//    }
}
