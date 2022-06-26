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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/home")
    public String homePage(Model model) {
        return homepagePaginated(1, "title", "asc", model);
    }

    @GetMapping("/home/{pageNo}")
    public String homepagePaginated(@PathVariable(value = "pageNo") int pageNo,
                                    @RequestParam("sortField") String sortField,
                                    @RequestParam("sortDir") String sortDir,
                                    Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByEmail(email);
            model.addAttribute("user", user);
        }
        int size = 5;
        List<Book> bookList = bookService.findBooksByTitleAsc();
        List<Book> books = bookService.findBooksPageable(sortField, sortDir, size, size * (pageNo - 1));
        List<BookStat> bookStats = bookStatService.findAllBookStat();
        model.addAttribute("books", books);
        model.addAttribute("bookStats", bookStats);
        model.addAttribute("book", new Book());
        model.addAttribute("maxTraySize", size);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", (int) Math.ceil(bookList.size() / size));
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "home";
    }

}
