package com.project.books.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class BookWithAuthorResponse {

    @Id
    private Integer id;
    private String bookTitle;
    private String bookDescription;
    private String authorName;
    private String authorEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public BookWithAuthorResponse(Integer id, String bookTitle, String bookDescription, String authorName, String authorEmail) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookDescription = bookDescription;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
    }
}
