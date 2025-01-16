# Library Management System

**Library Management System** is a web-based application built with **Spring Boot** for the backend and **React** for the frontend, designed to simplify library operations such as managing books, authors, users, and transactions. It enables users to search, borrow, and return books, while also allowing library staff to manage inventory and track user activities.

## Features

### User Features:
- **Search Books**: Easily search books by title, author, or genre.
- **Book Borrowing**: Users can borrow books by checking their availability.
- **Book Returning**: Simple interface to return borrowed books.
- **User Registration & Login**: Secure login and registration system for library users.

### Admin Features:
- **Manage Books**: Admins can add, update, and remove books from the library catalog.
- **User Management**: Admins can view and manage user accounts.
- **Book Transactions**: View borrowing and returning history of users.
- **Book Categories**: Admins can manage and categorize books for easy search.

### General Features:
- **Book Availability Check**: Real-time availability status for each book.
- **Responsive UI**: Fully responsive design using **React** and **Tailwind CSS**.
- **Smooth Navigation**: Intuitive navigation for both users and administrators.

## Technology Stack

- **Frontend**: React
  - React Router for navigation
  - Tailwind CSS for styling
  - Axios for API calls
- **Backend**: Spring Boot
  - RESTful APIs for data communication
  - Spring Security for authentication and authorization
  - JPA for database interaction
- **Database**: MySQL
  - Stores books, users, transactions, and other relevant data

## Key Features and Highlights

- **Spring Boot Security**: Secure user authentication and role-based access control.
- **CRUD Operations**: Add, update, delete, and fetch data for books and users.
- **Real-time Data**: Real-time updates on book availability and transaction status.
- **REST APIs**: Well-defined API endpoints for all interactions, including book management, user management, and transactions.
- **Responsive Frontend**: Fully responsive interface optimized for both desktop and mobile views.

## Screenshots

**Signup**  
![Library Signup](https://i.imgur.com/GGgNCPJ.png[/img])

**Admin Login**  
![Admin Login](https://i.imgur.com/sluzBMJ.png[/img])
 Note - Initially the username and password of the admin is same as the username and password you create the account

**Admin Dashboard**  
![Admin Dashboard](https://i.imgur.com/sWEQg8v.png[/img])

**Book Search**  
![Book Search](https://i.imgur.com/sWEQg8v.png[/img])
Note - The students accounts are created by admin and are assigned to the sudents for them to borrow the books 

## How to Run the Project Locally

### Prerequisites

- JDK 11 or higher
- Node.js (for React frontend)
- Maven (for Spring Boot backend)
- **MySQL Database**

### Backend Setup (Spring Boot)

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   ```
2. Navigate to the backend directory:
   ```bash
   cd backend
   ```
3. Install dependencies (if any):
   ```bash
   ./mvnw clean install
   ```
4. Configure MySQL database connection:
   
   In `src/main/resources/application.properties`, add your MySQL connection details:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/library_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   ```

   - Replace `localhost`, `3306`, `library_db`, `root`, and `your_password` with your actual MySQL database host, port, database name, username, and password.

5. Build and run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```
6. The backend server will start on `http://localhost:8080`.

### Frontend Setup (React)

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the React development server:
   ```bash
   npm start
   ```
4. The frontend will be available at `http://localhost:3000`.

### MySQL Database Setup

1. Install MySQL on your machine (if not already installed).
2. Create a new database for your application:
   ```sql
   CREATE DATABASE library_db;
   ```
3. You can optionally create tables by running SQL scripts, or let **Spring Boot** create them for you based on your entities.
4. Verify the connection by checking if data is being inserted into the MySQL database when you perform actions like adding or borrowing books.

## Future Enhancements

- **Notification System**: Email or SMS notifications for due dates and book availability.
- **User Rating and Reviews**: Allow users to rate and review books.
- **Admin Reports**: Generate reports on library statistics, such as popular books and active users.
- **Multi-Language Support**: Add support for multiple languages to reach a broader audience.

## Contributing

We welcome contributions! If you would like to improve the project, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make changes and commit them (`git commit -m "Add new feature"`).
4. Push to your branch (`git push origin feature-branch`).
5. Create a pull request.

