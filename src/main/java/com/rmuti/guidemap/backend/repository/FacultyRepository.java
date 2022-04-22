package com.rmuti.guidemap.backend.repository;

import com.rmuti.guidemap.backend.table.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, String> {

    //Optional<Faculty> findByTitle(String title);

    //boolean existsByEmail(String email);
}
