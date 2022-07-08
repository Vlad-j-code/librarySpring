package com.project.library.service.impl;

import com.project.library.entity.*;
import com.project.library.repository.*;
import com.project.library.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookStatRepository bookStatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookInBookingRepository bookInBookingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public void book(String email, Set<Book> books) {
        User user = userRepository.findByEmail(email);
        List<BookStat> stats = bookStatRepository.findBookStat();
        for (Book book : books) {
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setState(Booking.State.BOOKED);
            booking.setLocated(Booking.Place.LIBRARY);
            booking.setModified(Calendar.getInstance());
            bookingRepository.save(booking);
            BookInBooking bookInBooking = new BookInBooking();
            bookInBooking.setBookingId(booking.getId());
            bookInBooking.setBookId(book.getId());
            bookInBookingRepository.save(bookInBooking);
            BookStat bookStat = stats.stream().filter(item -> item.getBookId().equals(book.getId())).findFirst().get();
            bookStat.setReserved(bookStat.getReserved() + 1);
            bookStat.setTimesWasBooked(bookStat.getTimesWasBooked() + 1);
            bookStatRepository.save(bookStat);
        }
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findBookingList();
    }

    @Override
    public void subscribe(Long bookingId) {
        deliver(bookingId, Booking.Place.USER);
    }

    @Override
    public void inReadingRoom(Long bookingId) {
        deliver(bookingId, Booking.Place.LIBRARY);
    }

    private void deliver(Long bookingId, Booking.Place place) {
        Booking booking = bookingRepository.findBookingById(bookingId);
        booking.setState(Booking.State.DELIVERED);
        if (place.equals(Booking.Place.USER)) booking.setLocated(place);
        if (place.equals(Booking.Place.LIBRARY)) booking.setLocated(place);
        BookStat stat = getReservedBookStatByBookingId(booking.getId());
        stat.setReserved(stat.getReserved() - 1);
        stat.setInStock(stat.getInStock() - 1);
        booking.setModified(Calendar.getInstance());
        bookingRepository.save(booking);
        bookStatRepository.save(stat);
    }

    @Transactional
    @Override
    public void cancel(Long bookingId) {
        Booking booking = bookingRepository.findBookingById(bookingId);
        booking.setState(Booking.State.CANCELED);
        booking.setModified(Calendar.getInstance());
        BookStat stat = getReservedBookStatByBookingId(booking.getId());
        stat.setReserved(stat.getReserved() - 1);
        bookingRepository.save(booking);
        bookStatRepository.save(stat);
    }

    @Transactional
    @Override
    public void done(Long bookingId) {
        Booking booking = bookingRepository.findBookingById(bookingId);
        booking.setState(Booking.State.DONE);
        BookStat stat = getReservedBookStatByBookingId(booking.getId());
        stat.setInStock(stat.getInStock() + 1);
        booking.setModified(Calendar.getInstance());
        bookingRepository.save(booking);
        bookStatRepository.save(stat);
    }

    @Override
    public List<Book> getReservedBooks() {
        List<BookInBooking> bookInBookings = bookInBookingRepository.findAllBookInBooking();
        List<Book> books = bookRepository.findByTitleAsc();
        List<Book> reservedBooks = new ArrayList<>();
        for (BookInBooking b : bookInBookings) {
            for (Book book : books) {
                if (book.getId().equals(b.getBookId())) {
                    reservedBooks.add(book);
                }
            }
        }
        return reservedBooks;
    }

    @Override
    public BookStat getReservedBookStatByBookingId(Long id) {
        BookInBooking bookInBooking = bookInBookingRepository.findBookInBookingByBookingId(id);
        return bookStatRepository.findBookStatByBookId(bookInBooking.getBookId());
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findBookingById(id);
    }
}
