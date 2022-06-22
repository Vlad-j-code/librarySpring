package com.project.library.service;

import com.project.library.entity.Author;
import com.project.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<Book> findBooksByTitleAsc();

    List<Book> findBooksPageable(String order, int limit, int offset);

    Book findBookById(Long bookId);

    Book findBookByIsbn(String isbn);
}
