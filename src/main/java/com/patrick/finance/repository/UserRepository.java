package com.patrick.finance.repository;

import com.patrick.finance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, long> {
}
