package com.example.pratica_mobile.model;

public class Collaborator {

    private int id = 0;
    private String name;
    private int age;
    private String telephone;

    public Collaborator() {
    }

    public Collaborator(String name, int age, String telephone) {
        this.name = name;
        this.age = age;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
