package com.rmuti.guidemap.backend.controller;


import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.LocationException;
import com.rmuti.guidemap.backend.models.MLocationRequest;
import com.rmuti.guidemap.backend.services.LocationService;
import com.rmuti.guidemap.backend.table.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LocationController {

    private final LocationService locationService;


    public Location createLocation(MLocationRequest request) throws BaseException {
        Location location = locationService.createLocation(
                request.getName(),
                request.getAddress(),
                request.getLatitude(),
                request.getLongitude(),
                request.getImage(),
                request.getDetail()
        );
        Optional<Location> byName = locationService.findByName(location.getName());
        if(byName.isEmpty()){
            throw LocationException.locationCreateFail();
        }
        return byName.get();
    }

    public List<Location> getLocation() throws BaseException{
        List<Location> locationData = locationService.findAllLocation();
        if(locationData.isEmpty()){
            throw LocationException.locationFailDataNull();
        }
        return locationData;
    }

    public Location updateLocation(Location request) throws BaseException{
        Optional<Location> byId = locationService.findById(request.getId());
        if(byId.isEmpty()){
            throw LocationException.locationFailDataNull();
        }
        return locationService.updateLocation(
                request.getId(),
                request.getAddress(),
                request.getLatitude(),
                request.getLongitude(),
                request.getImage(),
                request.getDetail()
        );
    }




}
