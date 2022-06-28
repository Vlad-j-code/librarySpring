package com.project.library.service;

import com.project.library.entity.Author;
import com.project.library.entity.Book;
import com.project.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    Page<User> listUsers(Pageable pageable);

    void blockUnblockUserByEmail(String email);

    Page<Author> listAuthors(Pageable pageable);

    Author findAuthorByName(String name);

    void createAuthor(Author author);

    List<Author> findAuthors();

    void createBook(Book book, List<Author> authors);

    Long getIdLastBook();

    Long getIdNextBook();

    void updateBook(Long id, Book book);
}
