package com.project.library.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotEmpty(message="Can't be empty")
    @Column(name = "title")
    private String title;

    @NotEmpty(message="Can't be empty")
    @Column(name = "isbn", unique = true)
    private String isbn;

    @NotNull(message = "Can't be empty")
    @Column(name = "year")
    private Integer year;

    @NotEmpty(message="Can't be empty")
    @Length(min = 2, max = 2, message = "Must be 2 characters")
    @Column(name = "lang_code")
    private String langCode;

    @NotNull(message = "Can't be empty")
    @Column(name = "keep_period")
    private Long keepPeriod;

    @Column(name = "modified")
    private Calendar modified;

    @ManyToOne
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public Long getKeepPeriod() {
        return keepPeriod;
    }

    public void setKeepPeriod(Long keepPeriod) {
        this.keepPeriod = keepPeriod;
    }

    public Calendar getModified() {
        return modified;
    }

    public void setModified(Calendar modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn) && Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, isbn, year);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year=" + year +
                ", langCode='" + langCode + '\'' +
                ", keepPeriod=" + keepPeriod +
                ", modified=" + modified +
                '}';
    }
}
