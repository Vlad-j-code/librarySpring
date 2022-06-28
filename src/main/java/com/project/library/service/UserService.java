package com.project.library.service;


import com.project.library.entity.Book;
import com.project.library.entity.BookStat;
import com.project.library.entity.Booking;
import com.project.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void addLibrarian(User user);

    void deleteUser(String email);

    User findUserByEmail(String email);

    List<Booking> getUserBookings(String email);

    List<Book> getReservedBooksByBookings(List<Booking> bookings);

    List<Book> getDeliveredBooks(String email);

    List<Booking> getDeliveredBookingsWithReturnDate(String email);
}
