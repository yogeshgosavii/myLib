package com.example.myspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.apache.naming.factory.SendMailFactory;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

	@Id
	@Column(nullable = false)
	String studentId;
	String studentName;
	String studentPassword;
	String studentEmail;

	@JsonIgnore
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	List<BorrowedBook> borrowedBooks;

	@JoinColumn(name = "user_id")
	@ManyToOne()
	User user;


}
