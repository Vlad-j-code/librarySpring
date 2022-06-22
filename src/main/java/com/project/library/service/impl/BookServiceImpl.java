package com.project.library.service.impl;

import com.project.library.entity.Book;
import com.project.library.repository.BookRepository;
import com.project.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findBooksByTitleAsc() {
        return bookRepository.findByTitleAsc();
    }

    @Override
    public List<Book> findBooksPageable(String order, int limit, int offset) {
        return bookRepository.findBooksPage(order, limit, offset);
    }

    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }
}
