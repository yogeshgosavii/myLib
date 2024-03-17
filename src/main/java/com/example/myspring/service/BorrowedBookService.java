package com.example.myspring.service;

import com.example.myspring.entity.Book;
import com.example.myspring.entity.BorrowedBook;
import com.example.myspring.entity.Student;
import com.example.myspring.entity.User;

import java.util.List;

public interface BorrowedBookService {
    public void addBorrowedBook(BorrowedBook borrowedBook);

    public boolean hasBorrowedBook(Student student, Book book, User user);

    public List<BorrowedBook> getBorrowedBooks(User user);

    void deleteBorrowedBook(Student student, Book book, User user);
    boolean checkLimitReached(Long limit,Student student, User user);
    BorrowedBook checkReturnPending (Student student, User user);
}
