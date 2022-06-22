package com.project.library.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "book_stat")
public class BookStat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "total")
    private Long total;

    @Column(name = "in_stock")
    private Long inStock;

    @Column(name = "reserved")
    private Long reserved;

    @Column(name = "times_was_booked")
    private Long timesWasBooked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getInStock() {
        return inStock;
    }

    public void setInStock(Long inStock) {
        this.inStock = inStock;
    }

    public Long getReserved() {
        return reserved;
    }

    public void setReserved(Long reserved) {
        this.reserved = reserved;
    }

    public Long getTimesWasBooked() {
        return timesWasBooked;
    }

    public void setTimesWasBooked(Long timesWasBooked) {
        this.timesWasBooked = timesWasBooked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStat bookStat = (BookStat) o;
        return Objects.equals(total, bookStat.total)
                && Objects.equals(inStock, bookStat.inStock) && Objects.equals(reserved, bookStat.reserved)
                && Objects.equals(timesWasBooked, bookStat.timesWasBooked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, inStock, reserved, timesWasBooked);
    }

    @Override
    public String toString() {
        return "BookStat{" +
                ", total=" + total +
                ", inStock=" + inStock +
                ", reserved=" + reserved +
                ", timesWasBooked=" + timesWasBooked +
                '}';
    }
}
