package com.example.myspring.service;

import com.example.myspring.entity.User;
import com.example.myspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean checkEmailAndPassword(String email, String password) {
        User user = userRepository.findByUserEmail(email);
        return passwordEncoder.matches(password,user.getUserPassword());
    }
}
