package com.example.dog;

import android.graphics.Bitmap;

public class Pet {
    private int id;
    private String name;
    private String description;
    private int age;
    private String sex;
    private int note;

    private Bitmap image;

    public Pet(int petId, String name, String description, int age, String sex, int note, Bitmap image) {
        this.id = petId;
        this.name = name;
        this.description = description;
        this.age = age;
        this.sex = sex;
        this.note = note;
        this.image = image;
    }
    public Pet(int petId, String petName) {
        this.id = petId;
        this.name = petName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAge() {
        return age;
    }


    public String getSex() {
        return sex;
    }


    public int getNote() {
        return note;
    }


}

