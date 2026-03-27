package com.example.socialmedia.repository;

import com.example.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneNumber(String phoneNumber);

    @Procedure(name = "register_user")
    void registerUser(@Param("p_phone") String phone, @Param("p_password") String password);

    @Procedure(name = "login_user")
    Long loginUser(@Param("p_phone") String phone, @Param("p_password") String password);
}