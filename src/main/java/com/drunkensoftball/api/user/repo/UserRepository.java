package com.drunkensoftball.api.user.repo;


import com.drunkensoftball.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
