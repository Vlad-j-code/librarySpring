package com.project.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "author_name_i18n")
public class I18AuthorName implements Serializable {

    @Id
    @ManyToOne
    private Lang langId;

    @Id
    @ManyToOne
    private Author authorId;

    @Column(name = "name")
    private String name;

    public Lang getLangId() {
        return langId;
    }

    public void setLangId(Lang langId) {
        this.langId = langId;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        I18AuthorName that = (I18AuthorName) o;
        return Objects.equals(langId, that.langId) && Objects.equals(authorId, that.authorId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(langId, authorId, name);
    }

    @Override
    public String toString() {
        return "I18AuthorName{" +
                "langId=" + langId +
                ", authorId=" + authorId +
                ", name='" + name + '\'' +
                '}';
    }
}
