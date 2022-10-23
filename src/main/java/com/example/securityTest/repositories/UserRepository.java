package com.example.securityTest.repositories;

import com.example.securityTest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Transactional
    User findByUsername(String username);
}
