package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.AuthException;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.BuildingException;
import com.rmuti.guidemap.backend.exception.UserException;
import com.rmuti.guidemap.backend.repository.BuildingRepository;
import com.rmuti.guidemap.backend.repository.UserRepository;
import com.rmuti.guidemap.backend.table.BuildingData;
//import com.rmuti.guidemap.backend.table.ChatRoom;
import com.rmuti.guidemap.backend.table.UserData;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Data
@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    //
//    public Optional<BuildingData> findByTitle(String title){
//        return buildingRepository.findByTitle(title);
//    }

    public  Iterable<BuildingData> findAll(){
        return buildingRepository.findAll();
    }

//    public BuildingData createBuilding(String title, String address ,double latitude, double longitude) throws BaseException {
//        /// validate
//        if (Objects.isNull(title)) {
//            throw BuildingException.buildingFailTitleNull();
//        }
//        /// verify
//        if (buildingRepository.existsByTitle(title)) {
//            throw BuildingException.buildingFailDuplicated();
//        }
//
//        /// save
//        BuildingData entity = new BuildingData();
//        entity.setTitle(title);
//        entity.setAddress(address);
//        entity.setLatitude(latitude);
//        entity.setLongitude(longitude);
//        return buildingRepository.save(entity);
//
//    }


}
