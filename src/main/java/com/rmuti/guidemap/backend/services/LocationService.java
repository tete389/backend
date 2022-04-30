package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.LocationException;
import com.rmuti.guidemap.backend.exception.UserException;
import com.rmuti.guidemap.backend.repository.LocationRepository;
import com.rmuti.guidemap.backend.table.Location;
//import com.rmuti.guidemap.backend.table.ChatRoom;
import com.rmuti.guidemap.backend.table.UserProfile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    //
    public List<Location> findAllLocation(){
        return locationRepository.findAll();
    }

    //
    public Optional<Location> findByName(String name) {
        return locationRepository.findByName(name);
    }

    //
    public Optional<Location> findById(String id) {
        return locationRepository.findById(id);
    }

    //
    public Location createLocation(String name, String address , double latitude, double longitude, File image, String detail) throws BaseException {
        /// validate
        if (Objects.isNull(name)) {
            throw LocationException.locationFailNameNull();
        }
        if (Objects.isNull(address)) {
            address = "ยังไม่มีข้อมูล";
        }
        if (Objects.isNull(detail)) {
            detail = "ยังไม่มีข้อมูล";
        }
        if (Objects.toString(latitude).isEmpty() || Objects.toString(longitude).isEmpty()) {
           throw LocationException.locationCreateFail();
        }
//        if (Objects.isNull(image)) {
//            // TODO
//           //image = null;
//        }
        /// verify
        if (locationRepository.existsByName(name)) {
            throw LocationException.locationFailDuplicated();
        }
        /// save
        Location entity = new Location();
        entity.setName(name);
        entity.setAddress(address);
        entity.setLatitude(latitude);
        entity.setLongitude(longitude);
        entity.setDetail(detail);
        entity.setImage(image);
        entity.setDetail(detail);
        return locationRepository.save(entity);
    }

    //
    public Location updateLocation(String id, String address , double latitude, double longitude, File image, String detail) throws BaseException {
        /// validate
        if (Objects.isNull(address)) {
            address = "ยังไม่มีข้อมูล";
        }
        if (Objects.isNull(detail)) {
            detail = "ยังไม่มีข้อมูล";
        }
        if (Objects.toString(latitude).isEmpty() || Objects.toString(longitude).isEmpty()) {
            throw LocationException.locationFailDataNull();
        }

        /// verify
        Optional<Location> opt = locationRepository.findById(id);
        if (opt.isEmpty()){
            throw LocationException.locationFailDataNull();
        }
        /// save
        Location entity = opt.get();
        entity.setAddress(address);
        entity.setLatitude(latitude);
        entity.setLongitude(longitude);
        entity.setImage(image);
        entity.setDetail(detail);
        return locationRepository.save(entity);
    }


}
