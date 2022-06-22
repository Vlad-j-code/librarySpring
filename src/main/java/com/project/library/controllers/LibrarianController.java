package com.project.library.controllers;

import com.project.library.entity.Book;
import com.project.library.entity.Booking;
import com.project.library.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LibrarianController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public String booking(Model model) {
        List<Booking> bookings = bookingService.getAllBooking();
        List<Book> reservedBooks = bookingService.getReservedBooks();
        model.addAttribute("bookings", bookings);
        model.addAttribute("reservedBooks", reservedBooks);
        model.addAttribute("booking", new Booking());
        return "librarianBooking";
    }

    @PostMapping(value = "/booking{id}", params = "subscribe")
    public String subscribe(@ModelAttribute("Id") Booking booking) {
        bookingService.subscribe(booking.getId());
        return "redirect:/booking";
    }

    @PostMapping(value = "/booking{id}", params = "room")
    public String readingRoom(@ModelAttribute("Id") Booking booking) {
        bookingService.inReadingRoom(booking.getId());
        return "redirect:/booking";
    }

    @PostMapping(value = "/booking{id}", params = "cancel")
    public String cancel(@ModelAttribute("Id") Booking booking) {
        bookingService.cancel(booking.getId());
        return "redirect:/booking";
    }

    @PostMapping(value = "/booking{id}", params = "finish")
    public String done(@ModelAttribute("Id") Booking booking) {
        bookingService.done(booking.getId());
        return "redirect:/booking";
    }
}
