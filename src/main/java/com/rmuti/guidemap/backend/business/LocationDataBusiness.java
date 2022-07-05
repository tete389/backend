package com.rmuti.guidemap.backend.business;


import com.rmuti.guidemap.backend.exception.*;
//import com.rmuti.guidemap.backend.models.MLocationRequest;
import com.rmuti.guidemap.backend.models.MLocationResponse;
import com.rmuti.guidemap.backend.services.LocationDataService;
import com.rmuti.guidemap.backend.services.TokenService;
import com.rmuti.guidemap.backend.table.LocationData;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LocationDataBusiness {

    private final LocationDataService locationService;

    private final ChatRoomBusiness chatRoomBusiness;

    private final TokenService tokenService;

    ///
    public String createLocation(LocationData request) throws BaseException {
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();


        LocationData locationData = locationService.createLocation(
                request.getLdName(),
                request.getLdAddress(),
                request.getLdLatitude(),
                request.getLdLongitude(),
                request.getLdImage(),
                request.getLdDetail()
        );
        Optional<LocationData> byName = locationService.findByLocationName(locationData.getLdName());
        if(byName.isEmpty()){
            throw LocationException.locationCreateFail();
        }
        return "location created!";
    }

    public MLocationResponse getLocation() throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        MLocationResponse locationData = locationService.findAllLocation();
        if(locationData == null){
            throw LocationException.locationFailDataNull();
        }
        return locationData;
    }

    ///
    public String updateLocation(LocationData request) throws BaseException{
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();

        LocationData res =  locationService.updateLocation(
                request.getLdId(),
                request.getLdName(),
                request.getLdAddress(),
                request.getLdLatitude(),
                request.getLdLongitude(),
                request.getLdImage(),
                request.getLdDetail()
        );

        return "location updated";
    }

    ///
    public String deleteLocation(String locationId) throws BaseException{
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();


        Optional<LocationData> findById = locationService.findById(locationId);
        if (findById.isEmpty()){
            throw LocationException.locationFailDataNull();
        }
        LocationData location = findById.get();


        String messageDeletedChatRoom = chatRoomBusiness.delChatRoomByLocation(location.getLdId());
        if(messageDeletedChatRoom.isEmpty()){
            throw LocationException.locationDeleteFail();
        }
        String messageDeletedLocation = locationService.deleteLocationById(locationId);
        if(messageDeletedLocation.isEmpty()){
            throw LocationException.locationDeleteFail();
        }

        return "location deleted!";
    }
}
