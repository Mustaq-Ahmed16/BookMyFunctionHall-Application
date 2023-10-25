package com.example.smartwed.Models;

import android.graphics.Bitmap;

public class AddHallModel {
    Bitmap image;
    String name;
    String description;
    String orderno;
    String price;
    String capacity;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    String location;


    public AddHallModel(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public AddHallModel() {
        this.name = name;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.orderno=orderno;
    }

    public String getOrderno() {
        return orderno;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
