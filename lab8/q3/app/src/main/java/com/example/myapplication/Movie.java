package com.example.myapplication;

public class Movie {
    int id;
    String name;
    String year;
    int rating;

    public Movie() {}

    public Movie(int id, String name, String year, int rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getYear() { return year; }
    public int getRating() { return rating; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setYear(String year) { this.year = year; }
    public void setRating(int rating) { this.rating = rating; }
}