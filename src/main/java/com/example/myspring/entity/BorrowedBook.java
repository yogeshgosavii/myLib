package com.example.myspring.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBook {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long borrowId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id",referencedColumnName = "studentId")
    Student student;

    @JsonIgnore
    @JoinColumn(name="userId")
    @ManyToOne
    User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="book_id",referencedColumnName = "bookId")
    Book book;
    Date borrowDate;
    Date returnDate;
}
