package com.example.myspring.controller;

import com.example.myspring.entity.*;
import com.example.myspring.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final StudentService studentService;


    private final BookService bookService;

    private final BorrowedBookService borrowedBookService;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminController(StudentService studentService, BookService bookService, BorrowedBookService borrowedBookService) {
        this.studentService = studentService;
        this.bookService = bookService;
        this.borrowedBookService = borrowedBookService;
    }


    @GetMapping("book/register")
    public String createBook(Model model) {
        Book book = new Book();
        model.addAttribute("book",book);
        return "create_book";
    }
    private static String generateRandomId() {
        // Create an instance of Random
        Random random = new Random();

        // Generate a random 6-digit ID
        int min = 100000; // Minimum 6-digit number
        int max = 999999; // Maximum 6-digit number
        int randomNum = random.nextInt((max - min) + 1) + min;

        // Convert the random number to a string
        return String.valueOf(randomNum);
    }

    @PostMapping("book/create")
    public String addNewBook(@ModelAttribute("book") Book book,@ModelAttribute("currentUser") User currentUser){
        book.setBookId("B"+ LocalDate.now().getYear()+generateRandomId());
        book.setUser(currentUser);
        bookService.addBook(book);
        return "redirect:/admin/books";
    }

    @GetMapping("books")
    public String adminBooks(Model model,@ModelAttribute("currentUser") User currentUser){
        model.addAttribute("books",bookService.getBookList(currentUser));
        return "admin_book_list";
    }




    @GetMapping("borrowed_books")
    public String adminBorrowedBooks(Model model,@ModelAttribute("currentUser") User currentUser){
        model.addAttribute("borrowed_books",borrowedBookService.getBorrowedBooks(currentUser));
        return "admin_borrowed_book_list";
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("borrowed_books/category")
    public List<BorrowedBook> getCategoryBorrowed(@RequestParam String category, @ModelAttribute("currentUser") User currentUser) {
        List<BorrowedBook> borrowedBooks = borrowedBookService.getBorrowedBooks(currentUser);
        if (category.equals("Overdue")) {
            List<BorrowedBook> overdueBooks = new ArrayList<>();

            Date currentDate = new Date();

            for (BorrowedBook borrowedBook : borrowedBooks) {
                if (borrowedBook.getReturnDate().before(currentDate)) {
                    overdueBooks.add(borrowedBook);
                }
            }

            return overdueBooks;
        }
        log.info("books : {}", borrowedBooks);
        return borrowedBooks;
    }

    @GetMapping("student/register")
    public String  createStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "create_student";
    }

    @PostMapping("student/create")
    public String saveStudent(@ModelAttribute("student") Student student,@ModelAttribute("currentUser") User currentUser) {
        student.setUser(currentUser);
        studentService.saveStudent(student);
        return "redirect:/admin/students";

    }

    @GetMapping("students/edit/register/{id}")
    public String editStudentForm(@PathVariable("id") String studentId, Model model) {
        model.addAttribute("student",studentService.getStudentById(studentId));
        return "create_student";
    }

    @GetMapping("students/delete/{id}")
    public String deleteStudent(@PathVariable("id") String studentId) {
        studentService.deleteStudentById(studentId);
        return "redirect:/admin/students";
    }

    @GetMapping("books/edit/register/{id}")
    public String editBookForm(@PathVariable("id") String bookId,Model model) {
        model.addAttribute("book",bookService.getBookById(bookId));
        return "create_book";
    }
    @GetMapping("books/delete/{id}")
    public String deleteBook(@PathVariable("id") String bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/books";
    }

    @GetMapping("/students")
    public String adminStudents(Model model,@ModelAttribute("currentUser") User currentUser){
        model.addAttribute("students",studentService.getAllStudent(currentUser));
        return "admin_page";
    }

    @GetMapping("/user-logout")
    public String logout(HttpSession session) {
        session.removeAttribute("currentUser");
        return "redirect:/user-login";
    }

    @GetMapping("/reset-password")
    public String getResetPasswordForm(){
        return "admin_reset_password";
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> getResetPasswordForm(@RequestParam String currentPassword, @RequestParam String newPassword, @ModelAttribute("currentUser") User currentUser){
        if (passwordEncoder.matches(currentPassword,currentUser.getUserPassword())){
            currentUser.setUserPassword(passwordEncoder.encode(newPassword));
            userService.addNewUser(currentUser);
            Admin admin = adminService.getAdminByUsername(currentUser.getUserEmail(),currentUser);
            admin.setPassword(passwordEncoder.encode(newPassword));
            adminService.createNewAdmin(admin);
            return ResponseEntity.ok("Password reset successful");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect current password");
    }
}
