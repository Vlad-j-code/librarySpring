package com.project.library.repository;

import com.project.library.entity.BookStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookStatRepository extends JpaRepository<BookStat, Long> {

    @Query(value = "SELECT * FROM book_stat", nativeQuery = true)
    List<BookStat> findBookStat();

    @Query(value = "SELECT * FROM book_stat WHERE book_id = ?", nativeQuery = true)
    BookStat findBookStatByBookId(Long bookId);

    @Modifying
    @Query(value = "INSERT INTO book_stat VALUES (DEFAULT, ?, 20, DEFAULT, DEFAULT, DEFAULT)", nativeQuery = true)
    @Transactional
    void insertBookStatByBookId(Long bookId);
}
