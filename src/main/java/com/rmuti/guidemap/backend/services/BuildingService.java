//package com.rmuti.guidemap.backend.services;
//
//import com.rmuti.guidemap.backend.exception.AuthException;
//import com.rmuti.guidemap.backend.exception.BaseException;
//import com.rmuti.guidemap.backend.exception.UserException;
//import com.rmuti.guidemap.backend.repository.BuildingRepository;
//import com.rmuti.guidemap.backend.repository.UserRepository;
//import com.rmuti.guidemap.backend.table.BuildingData;
////import com.rmuti.guidemap.backend.table.ChatData;
//import com.rmuti.guidemap.backend.table.LocationData;
//import com.rmuti.guidemap.backend.table.UserData;
//import lombok.Data;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//import java.util.Optional;
//
//@Data
//@Service
//public class BuildingService {
//
//    private final BuildingRepository buildingRepository;
//
//    public BuildingService(BuildingRepository buildingRepository) {
//        this.buildingRepository = buildingRepository;
//    }
//
//    //
//    public Optional<BuildingData> findByTitle(String title){
//        return buildingRepository.findByTitle(title);
//    }
//
//    public Optional<BuildingData> findByLocation(String location){
//        return buildingRepository.findByLocationId(location);
//    }
//
//    public  Iterable<BuildingData> findAll(){
//        return buildingRepository.findAll();
//    }
//
//    public BuildingData createBuilding(LocationData locationData,String title, String detail) {
//        /// validate
//
//        /// verify
//
//        /// save
//        BuildingData entity = new BuildingData();
//        entity.setLocation(locationData);
//        entity.setTitle(title);
//        entity.setDetail(detail);
//        return buildingRepository.save(entity);
//
//    }
//
//
//}
