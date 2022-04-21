//package com.rmuti.guidemap.backend.services;
//
//import com.rmuti.guidemap.backend.repository.BuildingRepository;
//import com.rmuti.guidemap.backend.table.BuildingData;
//import com.rmuti.guidemap.backend.table.LocationData;
//import lombok.Data;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Data
//@Service
//public class ChatService {
//
//    private final BuildingRepository buildingRepository;
//
//    public ChatService(BuildingRepository buildingRepository) {
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
