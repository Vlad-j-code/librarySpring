package com.project.library.repository;

import com.project.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book AS b JOIN book_author as ba ON ba.book_id = b.id " +
            "JOIN author AS a ON a.id = ba.author_id ORDER BY title", nativeQuery = true)
    List<Book> findByTitleAsc();

    @Query(value = "SELECT * FROM book JOIN book_author ON book_author.book_id = book.id " +
            "JOIN author ON author.id = book_author.author_id ORDER BY ? LIMIT ? OFFSET ?", nativeQuery = true)
    List<Book> findBooksPage(String order, int limit, int offset);

    @Query(value = "SELECT * FROM book AS b JOIN book_author as ba ON ba.book_id = b.id " +
            "JOIN author AS a ON a.id = ba.author_id where b.id = ?", nativeQuery = true)
    Book findBookById(Long id);

    @Query(value = "SELECT * FROM book AS b JOIN book_author as ba ON ba.book_id = b.id " +
            "JOIN author AS a ON a.id = ba.author_id WHERE b.isbn = ?", nativeQuery = true)
    Book findBookByIsbn(String isbn);

    @Query(value = "SELECT * FROM book AS b JOIN book_author as ba ON ba.book_id = b.id " +
            "JOIN author AS a ON a.id = ba.author_id ORDER BY b.id DESC LIMIT 1", nativeQuery = true)
    Book findLastBook();

    @Query(value = "SELECT MAX(id) FROM book", nativeQuery = true)
    Long findIdLastBook();

    @Modifying
    @Query(value = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, DEFAULT)", nativeQuery = true)
    @Transactional
    void insertBook(Long id, String title, String isbn, Integer year, String langCode, Long keepPeriod);

    @Modifying
    @Query(value = "UPDATE book SET title = ?, isbn = ?, year = ?, lang_code = ?, keep_period = ? WHERE id = ?", nativeQuery = true)
    @Transactional
    void updateBook(String title, String isbn, Integer year, String langCode, Long keepPeriod, Long id);
}
