package com.example.myspring.repository;

import com.example.myspring.entity.Book;
import com.example.myspring.entity.BorrowedBook;
import com.example.myspring.entity.Student;
import com.example.myspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Long> {

    Boolean existsByStudentAndBookAndUser(Student student,Book book,User user);
    void deleteByStudentAndBookAndUser(Student student,Book book,User user);

    List<BorrowedBook> findAllByUser(User user);
    BorrowedBook findByStudentAndBookAndUser(Student student, Book book, User user);
    List<BorrowedBook> findAllByStudentAndUser(Student student,User user);



}
