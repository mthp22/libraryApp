package com.lm.backend.book;

import com.lm.backend.common.BusinessException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private int totalCopies;
    private int availableCopies;
    @Version
    private long version;

    public Book(String isbn, String title, String author, String category, int totalCopies) {
        update(isbn, title, author, category, totalCopies);
        this.availableCopies = totalCopies;
    }

    public void update(String isbn, String title, String author, String category, int totalCopies) {
        int borrowed = this.totalCopies - this.availableCopies;
        if (totalCopies < borrowed) {
            throw new BusinessException("Total copies cannot be lower than currently borrowed copies");
        }
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies - borrowed;
    }

    public void borrowCopy() {
        if (availableCopies == 0) {
            throw new BusinessException("No copies are available");
        }
        availableCopies--;
    }

    public void returnCopy() {
        if (availableCopies >= totalCopies) {
            throw new BusinessException("All copies are already available");
        }
        availableCopies++;
    }
}
