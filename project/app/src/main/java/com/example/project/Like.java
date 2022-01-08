package com.example.project;

public class Like {
    private String garage;
    private String address;

    // creating constructor for our variables.
    public Like(String garage, String address) {
        this.garage = garage;
        this.address = address;
    }

    // creating getter and setter methods.
    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
