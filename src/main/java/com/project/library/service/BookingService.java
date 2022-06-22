package com.project.library.service;

import com.project.library.entity.Book;
import com.project.library.entity.BookStat;
import com.project.library.entity.Booking;

import java.util.List;

public interface BookingService {

    void book(String email, List<Book> books);

    List<Booking> getAllBooking();

    void subscribe(Long bookingId);

    void inReadingRoom(Long bookingId);

    void cancel(Long bookingId);

    void done(Long bookingId);

    List<Book> getReservedBooks();

    BookStat getReservedBookStatByBookingId(Long id);

    Booking getBookingById(Long id);

}
