package com.project.library.repository;

import com.project.library.entity.BookInBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookInBookingRepository extends JpaRepository<BookInBooking, Long> {

    @Query(value = "SELECT * FROM book_in_booking", nativeQuery = true)
    List<BookInBooking> findAllBookInBooking();

    @Query(value = "SELECT * FROM book_in_booking WHERE booking_id = ?", nativeQuery = true)
    BookInBooking findBookInBookingByBookingId(Long bookingId);
}
