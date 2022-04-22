package com.rmuti.guidemap.backend.repository;

import com.rmuti.guidemap.backend.table.Branch;
import com.rmuti.guidemap.backend.table.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {

//    Optional<UserData> findByEmail(String email);
//
//    boolean existsByEmail(String email);
}
