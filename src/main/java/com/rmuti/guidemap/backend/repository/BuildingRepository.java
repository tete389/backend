//package com.rmuti.guidemap.backend.repository;
//
//import com.rmuti.guidemap.backend.table.BuildingData;
//import com.rmuti.guidemap.backend.table.UserData;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.Optional;
//
//public interface BuildingRepository extends CrudRepository<BuildingData, String> {
//
//    Optional<BuildingData> findByTitle(String title);
//
//    Optional<BuildingData> findByLocationId(String location);
//}
