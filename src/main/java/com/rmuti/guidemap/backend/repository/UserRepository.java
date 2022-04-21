package com.rmuti.guidemap.backend.repository;

import java.util.Optional;

import com.rmuti.guidemap.backend.table.UserData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, String> {

    Optional<UserData> findByEmail(String email);

    boolean existsByEmail(String email);

}
