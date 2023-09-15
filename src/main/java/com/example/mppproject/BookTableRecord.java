package com.example.mppproject;

public final class BookTableRecord {
    private String title;
    private String isbn;
    private String authors;
    private String checkoutLength;
    private String numCopy;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCheckoutLength() {
        return checkoutLength;
    }

    public void setCheckoutLength(String checkoutLength) {
        this.checkoutLength = checkoutLength;
    }

    public String getNumCopy() {
        return numCopy;
    }

    public void setNumCopy(String numCopy) {
        this.numCopy = numCopy;
    }

    @Override
    public String toString() {
        return "BookTableRecord{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                ", checkoutLength='" + checkoutLength + '\'' +
                ", numCopy='" + numCopy + '\'' +
                '}';
    }
}
