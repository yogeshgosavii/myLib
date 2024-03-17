package com.example.myspring.service;

import com.example.myspring.entity.Admin;
import com.example.myspring.entity.User;
import com.example.myspring.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface AdminService {
    void createNewAdmin(Admin admin);
    Admin getAdminByUsername(String username , User user);
}
