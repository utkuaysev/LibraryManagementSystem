package com.example.mppproject;

public class OverdueInfo {

    private String isbn;
    private String copyNum;
    private String title;
    private String memberId;
    private String name;
    private String dueDate;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCopyNum() {
        return copyNum;
    }

    public void setCopyNum(String copyNum) {
        this.copyNum = copyNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "OverdueInfo{" +
                "isbn='" + isbn + '\'' +
                ", copyNum='" + copyNum + '\'' +
                ", title='" + title + '\'' +
                ", memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
