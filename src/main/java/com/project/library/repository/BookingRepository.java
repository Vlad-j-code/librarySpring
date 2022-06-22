package com.project.library.repository;

import com.project.library.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM booking AS bo JOIN user AS u ON u.id = bo.user_id JOIN book_in_booking AS bib ON bib.booking_id = bo.id JOIN book AS b ON b.id = bib.book_id", nativeQuery = true)
    List<Booking> findBookingList();

    @Query(value = "SELECT * FROM booking WHERE id = ?", nativeQuery = true)
    Booking findBookingById(Long id);

    @Query(value = "SELECT * FROM booking WHERE user_id = ?", nativeQuery = true)
    List<Booking> findBookingListByUser(Long userId);

    @Query(value = "SELECT * FROM booking WHERE user_id = ? AND state = 'DELIVERED'", nativeQuery = true)
    List<Booking> findBookingDeliveredListByUser(Long userId);
}
