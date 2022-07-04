package com.project.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BookService bookService;

    private static final String author = "Bob Dylan";

    @Test
    public void findUsers() {
        assertFalse(adminService.listUsers(PageRequest.of(0, 5, Sort.by("email"))).isEmpty());
    }

    @Test
    public void findAuthors() {
        assertFalse(adminService.listAuthors(PageRequest.of(0, 5, Sort.by("name"))).isEmpty());
    }

    @Test
    public void findAuthorByName() {
        assertEquals(author, adminService.findAuthorByName(author).getName());
    }

    @Test
    public void findBooks() {
        assertFalse(bookService.findBooksByTitleAsc().isEmpty());
    }

}
