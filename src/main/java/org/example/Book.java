package org.example;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private int pages;
    private String category;
    private boolean isOnLoan;
    private LocalDate loanDate;

    // Constructor
    public Book(String title, String author, int publicationYear, int pages, String category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.category = category;
        this.isOnLoan = false;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public String getCategory() {
        return category;
    }

    public boolean isOnLoan() {
        return isOnLoan;
    }


    public LocalDate getLoanDate() {
        return loanDate;
    }
    public void setOnLoan(boolean onLoan, LocalDate loanDate) {
        this.isOnLoan = onLoan;
        this.loanDate = loanDate;
    }
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + "'" +
                ", author='" + author + "'" +
                ", publicationYear=" + publicationYear +
                ", pages=" + pages +
                ", category='" + category + "'" +
                ", isOnLoan=" + isOnLoan +
                '}';
    }
}

