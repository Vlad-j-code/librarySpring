package com.project.library.service.impl;

import com.project.library.entity.BookInBooking;
import com.project.library.repository.BookInBookingRepository;
import com.project.library.service.BookInBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInBookingServiceImpl implements BookInBookingService {

    @Autowired
    private BookInBookingRepository bookInBookingRepository;

    @Override
    public List<BookInBooking> getAll() {
        return bookInBookingRepository.findAllBookInBooking();
    }
}
