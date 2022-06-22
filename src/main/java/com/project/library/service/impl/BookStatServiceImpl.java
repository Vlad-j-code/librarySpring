package com.project.library.service.impl;

import com.project.library.entity.BookStat;
import com.project.library.repository.BookStatRepository;
import com.project.library.service.BookStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStatServiceImpl implements BookStatService {

    @Autowired
    private BookStatRepository bookStatRepository;

    @Override
    public List<BookStat> findAllBookStat() {
        return bookStatRepository.findBookStat();
    }
}
