package com.project.library.controllers;

import com.project.library.entity.Book;
import com.project.library.entity.Booking;
import com.project.library.service.BookService;
import com.project.library.service.BookingService;
import com.project.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    private final List<Book> basket = new ArrayList<>();

    @PostMapping(value = "/{id}")
    public String addBookToBasket(@ModelAttribute("Id") Book book, Model model) {
        Book selectedBook = bookService.findBookById(book.getId());
        basket.add(selectedBook);
        model.addAttribute("message", "Added to basket");
        return "redirect:/";
    }

    @GetMapping("/basket")
    public String basket(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            List<Booking> bookings = userService.getUserBookings(email);
            List<Book> books = userService.getReservedBooksByBookings(bookings);
            model.addAttribute("bookings", bookings);
            model.addAttribute("books", books);
        }
        return "basket";
    }

    @PostMapping(value = "/basket", params = "submit")
    public String finishBooking(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            bookingService.book(email, basket);
            basket.clear();
            model.addAttribute("message", "Books has been booked");
        }
        return "basket";
    }

    @PostMapping(value = "/basket", params = "cancel")
    public String cancel(Model model) {
        basket.clear();
        model.addAttribute("message", "Basket has been cleared");
        return "basket";
    }

    @GetMapping("/subscriptions")
    public String myBooks(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            List<Book> books = userService.getDeliveredBooks(email);
            List<Booking> bookings = userService.getDeliveredBookingsWithReturnDate(email);
            model.addAttribute("books", books);
            model.addAttribute("bookings", bookings);
        }
        return "my_books";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "email",
            required = false) String login, Model model, HttpServletRequest request, HttpSession session) {
        if (error != null) {
            model.addAttribute("error", "Invalid Credentials!");
            return "login";
        }
        session.invalidate();
        HttpSession newSession = request.getSession();
        newSession.setAttribute("cart", basket);
        if (newSession.isNew()) {
            basket.clear();
        }
        return "login";
    }
}
