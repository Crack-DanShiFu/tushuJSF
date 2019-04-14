package com.library.crack.model;

public class Books {
    private String bookName;
    private String publicationTime;
    private String bookAuthor;
    private String BookType;
    private String bookPrice;
    private String bookDescription;

    public Books() {

    }

    public Books(String bookName, String publicationTime, String bookAuthor, String bookType, String bookPrice, String bookDescription) {
        this.bookName = bookName;
        this.publicationTime = publicationTime;
        this.bookAuthor = bookAuthor;
        BookType = bookType;
        this.bookPrice = bookPrice;
        this.bookDescription = bookDescription;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookType() {
        return BookType;
    }

    public void setBookType(String bookType) {
        BookType = bookType;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
