package com.example.notesapp;

public class products {
    private String name;
    private String price;
    private boolean isAvailable;

    public products(String name, String price, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;

    }

    public products() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean getisAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "products{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
