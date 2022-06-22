package com.project.library.controllers;

import com.project.library.entity.Author;
import com.project.library.entity.Book;
import com.project.library.entity.BookStat;
import com.project.library.entity.User;
import com.project.library.service.AdminService;
import com.project.library.service.BookService;
import com.project.library.service.BookStatService;
import com.project.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    private final List<Author> authorList = new ArrayList<>();
    private final List<Book> bookList = new ArrayList<>();

    @GetMapping("/users")
    public String users(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                        @RequestParam(value = "size", defaultValue = "5", required = false) Integer size,
                        Model model) {
        Page<User> users = adminService.listUsers(PageRequest.of(page, size, Sort.by("email")));
        model.addAttribute("user", new User());
        model.addAttribute("users", users);
        model.addAttribute("maxTraySize", size);
        model.addAttribute("currentPage", page);
        return "adminUsers";
    }

    @PostMapping(value = "/users{email}", params = "block")
    public String blockUnblockUsers(@ModelAttribute("Email") User user) {
        adminService.blockUnblockUserByEmail(user.getEmail());
        return "redirect:/users";
    }

    @PostMapping(value = "/users{email}", params = "delete")
    public String deleteLibrarian(@ModelAttribute("Email") User user) {
        userService.deleteUser(user.getEmail());
        return "redirect:/users";
    }

    @GetMapping("/createLibrarian")
    public String registerLibrarianGet(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "adminCreateLibrarian";
    }

    @PostMapping("/createLibrarian")
    public String registerLibrarianPost(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            model.addAttribute("loginMsg", "The user is already registered with the specified email");
            return "adminCreateLibrarian";
        }
        if (bindingResult.hasErrors()) {
            return "adminCreateLibrarian";
        } else {
            userService.addLibrarian(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/authors")
    public String authors(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                          @RequestParam(value = "size", defaultValue = "5", required = false) Integer size,
                          Model model) {
        Page<Author> authors = adminService.listAuthors(PageRequest.of(page, size, Sort.by("name")));
        model.addAttribute("author", new Author());
        model.addAttribute("authors", authors);
        model.addAttribute("maxTraySize", size);
        model.addAttribute("currentPage", page);
        return "adminAuthors";
    }

    @GetMapping("/createAuthor")
    public String createAuthorGet(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "adminCreateAuthor";
    }

    @PostMapping("/createAuthor")
    public String createAuthorPost(@Valid @ModelAttribute("author") Author author, BindingResult bindingResult, Model model) {
        Author authorExists = adminService.findAuthorByName(author.getName());
        if (authorExists != null) {
            model.addAttribute("authorMsg", "Author name already exists");
            return "adminCreateAuthor";
        }
        if (bindingResult.hasErrors()) {
            return "adminCreateAuthor";
        } else {
            adminService.createAuthor(author);
        }
        return "redirect:/authors";
    }

    @GetMapping("/createBook")
    public String createBookGet(Model model) {
        if (authorList.isEmpty()) {
            model.addAttribute("authorMsg", "Author not selected");
            return "adminCreateBook";
        }
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorList);
        return "adminCreateBook";
    }

    @PostMapping("/createBook")
    public String createBookPost(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        Book bookExists = bookService.findBookByIsbn(book.getIsbn());
        if (bookExists != null) {
            model.addAttribute("bookMsg", "Such ISBN already exists");
            return "adminCreateBook";
        }
        if (bindingResult.hasErrors()) {
            return "adminCreateBook";
        } else {
            adminService.createBook(book, authorList);
        }
        return "redirect:/authors";
    }

    @GetMapping("/chooseAuthor")
    public String chooseAuthorGet(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        List<Author> authors = adminService.findAuthors();
        model.addAttribute("authors", authors);
        return "adminChooseAuthor";
    }

    @PostMapping("/chooseAuthor{name}")
    public String chooseAuthorPost(@ModelAttribute("Name") Author author) {
        authorList.clear();
        Author selectedAuthor = adminService.findAuthorByName(author.getName());
        authorList.add(selectedAuthor);
        return "redirect:/createBook";
    }

    @PostMapping(value = "/", params = "edit")
    public String redirectToEditBook(@ModelAttribute("Id") Book book) {
        if (!bookList.isEmpty()) {
            bookList.clear();
        }
        Book selectedBook = bookService.findBookById(book.getId());
        bookList.add(selectedBook);
        return "redirect:/editBook";
    }

    @GetMapping("/editBook")
    public String editBookGet(Model model) {
        if (bookList.isEmpty()) {
            model.addAttribute("bookMsg", "Book not selected");
            return "adminEditBook";
        }
        model.addAttribute("book", bookList.get(0));
        return "adminEditBook";
    }

    @PostMapping(value = "/editBook")
    public String editBookPost(@Valid @ModelAttribute("book") Book book) {
        Book selectedBook = bookService.findBookById(bookList.get(0).getId());
        adminService.updateBook(selectedBook.getId(), book);
        bookList.clear();
        return "redirect:/";
    }

}
