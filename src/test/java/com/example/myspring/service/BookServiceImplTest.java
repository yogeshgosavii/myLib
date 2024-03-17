package com.example.myspring.service;

import com.example.myspring.entity.Book;
import com.example.myspring.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BookServiceImplTest {
    @Autowired
    BookRepository bookRepository;

    BookServiceImplTest(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @Test
    public void  getBookList() {
         System.out.println(bookRepository.findAll());
    }

}