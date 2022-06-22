package com.project.library.controllers;

import com.project.library.entity.Book;
import com.project.library.entity.BookStat;
import com.project.library.entity.User;
import com.project.library.service.BookService;
import com.project.library.service.BookStatService;
import com.project.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookStatService bookStatService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homepage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByEmail(email);
            model.addAttribute("user", user);
        }
        List<Book> books = bookService.findBooksByTitleAsc();
        List<BookStat> bookStats = bookStatService.findAllBookStat();
        model.addAttribute("books", books);
        model.addAttribute("bookStats", bookStats);
        model.addAttribute("book", new Book());
        return "home";
    }

}
