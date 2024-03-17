package com.example.myspring.advice;

import com.example.myspring.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.Objects;

@Aspect
@Component
@Slf4j
public class CheckCurrentUser {

    private final HttpSession session;

    public CheckCurrentUser(HttpSession session) {
        this.session = session;
    }

    private User getCurrentUser() {
        if (session != null) {
            return (User) session.getAttribute("currentUser");
        }
        return null;
    }

    @Before("execution(* com.example.myspring.controller.*.*(..)) && !execution(* com.example.myspring.controller.UserController.*(..))")
    public void beforeControllerMethodExecution() {
        User currentUser = getCurrentUser();
        log.info("currentUser : {}",currentUser);
        // Check your specific condition
        if (currentUser == null) {
            // Get HttpServletRequest object
            HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
            // Redirect to the specified URL
            try {
                assert response != null;
                response.sendRedirect("/user-login");
            } catch (Exception e) {
                log.error("Error redirecting to login page: {}", e.getMessage());
            }
        }
    }

    @Before("execution(* com.example.myspring.controller.UserController.*(..))")
    public void beforeLoginOrSignup(){
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            // Get HttpServletRequest object
            HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
            // Redirect to the specified URL
            try {
                assert response != null;
                response.sendRedirect("/");
            } catch (Exception e) {
                log.error("Error redirecting to student: {}", e.getMessage());
            }
        }
    }
}
