package com.project.library.service.impl;

import com.project.library.entity.Author;
import com.project.library.entity.Book;
import com.project.library.entity.BookAuthor;
import com.project.library.entity.User;
import com.project.library.repository.*;
import com.project.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    private BookStatRepository bookStatRepository;

    @Override
    public Page<User> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public void blockUnblockUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user.getActive() == 1) {
            user.setActive(0);
        } else user.setActive(1);
        userRepository.save(user);
    }

    @Override
    public Page<Author> listAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    @Transactional
    @Override
    public void createAuthor(Author author) {
        author.setModified(Calendar.getInstance());
        authorRepository.save(author);
    }

    @Override
    public List<Author> findAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void createBook(Book book, List<Author> authors) {
        Author author = authors.get(0);
        bookRepository.insertBook(getIdNextBook(), book.getTitle(), book.getIsbn(), book.getYear(), book.getLangCode(), book.getKeepPeriod());
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setBookId(getIdLastBook());
        bookAuthor.setAuthorId(author.getId());
        bookStatRepository.insertBookStatByBookId(getIdLastBook());
        bookAuthorRepository.save(bookAuthor);
    }

    @Override
    public Long getIdLastBook() {
        return bookRepository.findIdLastBook();
    }

    @Override
    public Long getIdNextBook() {
        return Long.sum(getIdLastBook(), 1);
    }

    @Override
    public void updateBook(Long id, Book book) {
        bookRepository.updateBook(book.getTitle(), book.getIsbn(), book.getYear(), book.getLangCode(), book.getKeepPeriod(), id);
    }

}
