package com.rmuti.guidemap.backend.repository;

import com.rmuti.guidemap.backend.table.LocationData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationDataRepository extends JpaRepository<LocationData, String> {

    Optional<LocationData> findByLdName(String name);

    boolean existsByLdName(String title);

}
