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
    public List<Book> findBooksPageable(String sortField, String order, int limit, int offset) {
        if (order.equals("asc")) {
            switch (sortField) {
                case "title": return bookRepository.findBooksPageByTitleAsc(limit, offset);
                case "author": return bookRepository.findBooksPageByAuthorAsc(limit, offset);
                case "isbn": return bookRepository.findBooksPageByIsbnAsc(limit, offset);
                case "year": return bookRepository.findBooksPageByYearAsc(limit, offset);
            }
        }
        if (order.equals("desc")) {
            switch (sortField) {
                case "title": return bookRepository.findBooksPageByTitleDesc(limit, offset);
                case "author": return bookRepository.findBooksPageByAuthorDesc(limit, offset);
                case "isbn": return bookRepository.findBooksPageByIsbnDesc(limit, offset);
                case "year": return bookRepository.findBooksPageByYearDesc(limit, offset);
            }
        }
        return bookRepository.findBooksPageByTitleAsc(limit, offset);
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
