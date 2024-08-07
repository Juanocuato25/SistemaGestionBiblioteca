package com.jarenas.msvc.user.msvc_user.repository;

import com.jarenas.msvc.user.msvc_user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{
 boolean existsByEmail(String email);
}
