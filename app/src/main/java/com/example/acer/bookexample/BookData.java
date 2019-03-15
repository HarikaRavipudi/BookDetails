package com.example.acer.bookexample;

import android.widget.ImageView;

class BookData {
    String title,author,description,image;


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BookData(String title, String author, String description, String image) {
        this.title = title;
        this.author = author;
        this.description = description;
       this.image=image;
    }
}
