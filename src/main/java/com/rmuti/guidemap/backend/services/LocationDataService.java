package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.exception.LocationException;
import com.rmuti.guidemap.backend.models.MLocationResponse;
import com.rmuti.guidemap.backend.repository.LocationDataRepository;
import com.rmuti.guidemap.backend.table.LocationData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocationDataService {

    private final LocationDataRepository locationRepository;

    public LocationDataService(LocationDataRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    //
    public MLocationResponse findAllLocation(){
        MLocationResponse res = new MLocationResponse();
        res.getResult().addAll(locationRepository.findAll());
        return res;
    }
//
//    public List<LocationData> findAllLocation(){
//    return locationRepository.findAll();
//}
    //
    public String deleteLocationById(String locationId) throws BaseException{
        if (Objects.isNull(locationId)) {
            throw LocationException.locationFailDataNull();
        }
        locationRepository.deleteById(locationId);

        return "delete Location ById Success";
    }

    //
    public Optional<LocationData> findByLocationName(String locationName) {
        return locationRepository.findByLdName(locationName);
    }

    //
    public Optional<LocationData> findById(String id) {
        return locationRepository.findById(id);
    }

    //
    public LocationData createLocation(String locationName, String address , double latitude, double longitude, String image, String detail) throws BaseException {
        /// validate
        if (Objects.isNull(locationName)) {
            throw LocationException.locationFailNameNull();
        }
        if (Objects.isNull(address)) {
            address = "No Data";
        }
        if (Objects.isNull(detail)) {
            detail = "No Data";
        }
        if (Objects.isNull(image)) {
            image = "No Data";
        }
        if (Objects.toString(latitude).isEmpty() || Objects.toString(longitude).isEmpty()) {
           throw LocationException.locationCreateFail();
        }

        /// verify
        if (locationRepository.existsByLdName(locationName)) {
            throw LocationException.locationFailDuplicated();
        }
        /// save
        LocationData entity = new LocationData();
        entity.setLdName(locationName);
        entity.setLdAddress(address);
        entity.setLdLatitude(latitude);
        entity.setLdLongitude(longitude);
        entity.setLdDetail(detail);
        entity.setLdImage(image);
        return locationRepository.save(entity);
    }

    //
    public LocationData updateLocation(String id, String name, String address , double latitude, double longitude, String image, String detail) throws BaseException {
        /// validate
        Optional<LocationData> opt = locationRepository.findById(id);
        if (opt.isEmpty()){
            throw LocationException.locationUpdateFail();
        }
        LocationData res = opt.get();

        if (Objects.isNull(address)) {
            address = res.getLdAddress();
        }
        if (Objects.isNull(detail)) {
            detail = res.getLdDetail();
        }
        if (Objects.isNull(image)) {
            image = res.getLdImage();
        }
        if (Objects.toString(latitude).isEmpty()) {
            latitude = res.getLdLatitude();
        }
        if (Objects.toString(longitude).isEmpty()){
            longitude = res.getLdLongitude();
        }

        /// verify
        if (locationRepository.existsByLdName(name)) {
            if(!res.getLdName().equals(name)) {
                throw LocationException.locationFailDuplicated();
            }
        }
        if(Objects.isNull(name)) {
            name = res.getLdName();
        }

        /// update
        LocationData entity = opt.get();
        entity.setLdName(name);
        entity.setLdAddress(address);
        entity.setLdLatitude(latitude);
        entity.setLdLongitude(longitude);
        entity.setLdImage(image);
        entity.setLdDetail(detail);
        return locationRepository.save(entity);
    }


}
