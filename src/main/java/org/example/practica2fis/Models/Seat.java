package org.example.practica2fis.Models;

import javafx.scene.shape.Circle;

public class Seat
{
    private int price, id;
    private char zone;
    private boolean available;
    private Circle circle;

    public Seat(char zone, int id)
    {
        this.zone = zone;
        this.id = id;
        this.available = true;
        price = this.zone == 'A' ? 10000 : this.zone == 'B' ? 8000 : 5000 ;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Circle getCircle() {
        return circle;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public char getZone() {
        return zone;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString(){
        return "Seat no: " + this.id +
                "\nZone: " + this.zone +
                "\nPrice: $" + this.price;
    }
}

