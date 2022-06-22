package com.project.library.service.impl;

import com.project.library.entity.Book;
import com.project.library.entity.BookInBooking;
import com.project.library.entity.Booking;
import com.project.library.entity.User;
import com.project.library.repository.BookInBookingRepository;
import com.project.library.repository.BookRepository;
import com.project.library.repository.BookingRepository;
import com.project.library.repository.UserRepository;
import com.project.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookInBookingRepository bookInBookingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public void addUser(User user) {
        add(user, User.Role.USER);
    }

    private void add(User user, User.Role role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setFineLastChecked(Calendar.getInstance());
        user.setModified(Calendar.getInstance());
        if (role.equals(User.Role.USER)) user.setRole(role);
        if (role.equals(User.Role.LIBRARIAN)) user.setRole(role);
        user.setActive(1);
        user.setPreferredLang(1);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void addLibrarian(User user) {
        add(user, User.Role.LIBRARIAN);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }

    @Override
    public User findUser(long userId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Booking> getUserBookings(String email) {
        User user = userRepository.findByEmail(email);
        return bookingRepository.findBookingListByUser(user.getId());
    }

    @Override
    public List<Book> getReservedBooksByBookings(List<Booking> bookings) {
        List<BookInBooking> bookInBookings = new ArrayList<>();
        for (Booking b : bookings) {
            bookInBookings.add(bookInBookingRepository.findBookInBookingByBookingId(b.getId()));
        }
        List<Book> books = new ArrayList<>();
        for (BookInBooking bb : bookInBookings) {
            books.add(bookRepository.findBookById(bb.getBookId()));
        }
        return books;
    }

    @Override
    public List<Book> getDeliveredBooks(String email) {
        return getReservedBooksByBookings(getBookings(email));
    }

    @Override
    public List<Booking> getDeliveredBookingsWithReturnDate(String email) {
        List<Booking> bookings = getBookings(email);
        List<Book> books = getReservedBooksByBookings(bookings);
        for (int i = 0; i < bookings.size(); i++) {
            bookings.get(i).getModified().add(Calendar.DATE, Math.toIntExact(books.get(i).getKeepPeriod()));
        }
        return bookings;
    }

    private List<Booking> getBookings(String email) {
        User user = userRepository.findByEmail(email);
        return bookingRepository.findBookingDeliveredListByUser(user.getId());
    }
}
