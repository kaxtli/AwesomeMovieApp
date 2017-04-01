package com.kaxtli.models;

/**
 * Created by kaxtli on 11/03/2017.
 * Describe el contenido de una película
 */

public class Movie {
    private int id;
    private String title;
    private String description;
    private String poster_path;

    public Movie(int id, String title, String description, String poster_path) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
