package com.example.socialmedia.repository;

import com.example.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Procedure(name = "register_user")
    void registerUser(@Param("p_user_name") String userName, @Param("p_email") String email, @Param("p_password") String password, @Param("p_biography") String biography);

    @Procedure(name = "login_user")
    Long loginUser(@Param("p_email") String email, @Param("p_password") String password);
}