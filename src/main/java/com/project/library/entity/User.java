package com.project.library.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotEmpty(message="Can't be empty")
    @Column(name="email")
    private String email;

    @NotEmpty(message="Can't be empty")
    @Length(min = 5, message="Most be longer than 5")
    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "active")
    private int active;

    @Column(name = "fine")
    private double fine;

    @NotEmpty(message="Can't be empty")
    @Column(name = "name")
    private String name;

    @Column(name = "preferred_lang_id")
    private int preferredLang;

    @Column(name = "modified")
    private Calendar modified;

    @Column(name = "fine_last_checked")
    private Calendar fineLastChecked;

    /**
     * User roles, used in authorization
     */
    public enum Role {
        UNKNOWN, USER, LIBRARIAN, ADMIN
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

//    public Double getFine() {
//        return fine;
//    }
//
//    public void setFine(Double fine) {
//        this.fine = fine;
//    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Lang getPreferredLang() {
//        return preferredLang;
//    }
//
//    public void setPreferredLang(Lang preferredLang) {
//        this.preferredLang = preferredLang;
//    }

    public int getPreferredLang() {
        return preferredLang;
    }

    public void setPreferredLang(int preferredLang) {
        this.preferredLang = preferredLang;
    }

    public Calendar getModified() {
        return modified;
    }

    public void setModified(Calendar modified) {
        this.modified = modified;
    }

    public Calendar getFineLastChecked() {
        return fineLastChecked;
    }

    public void setFineLastChecked(Calendar fineLastChecked) {
        this.fineLastChecked = fineLastChecked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", active=" + active +
                ", fine=" + fine +
                ", name='" + name + '\'' +
                ", preferredLang=" + preferredLang +
                ", modified=" + modified +
                ", fineLastChecked=" + fineLastChecked +
                '}';
    }
}