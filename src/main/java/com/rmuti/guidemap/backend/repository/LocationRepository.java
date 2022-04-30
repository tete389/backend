package com.rmuti.guidemap.backend.repository;

import com.rmuti.guidemap.backend.table.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, String> {

    Optional<Location> findByName(String title);

    boolean existsByName(String title);

}
