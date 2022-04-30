package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.controller.LocationController;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.models.MLocationRequest;
import com.rmuti.guidemap.backend.table.Location;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/build")
public class LocationApi {

    //
    private final LocationController locationController;

    public LocationApi(LocationController locationController) {
        this.locationController = locationController;
    }

    //
    @PostMapping("/createLocation")
    public ResponseEntity<Location> createLocation(@RequestBody MLocationRequest request) throws BaseException {
        Location locationData = locationController.createLocation(request);
        return ResponseEntity.ok(locationData);
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @GetMapping("/getLocation")
    public List<Location> getLocation() throws BaseException {
        return locationController.getLocation();
    }

//    @GetMapping("/getLocation2")
//    public ResponseEntity<Location> getLocation() throws BaseException {
//        return ResponseEntity.ok();
//    }

    @PostMapping("/updateLocation")
    public ResponseEntity<Location> createLocation(@RequestBody Location request) throws BaseException {
        Location locationData = locationController.updateLocation(request);
        return ResponseEntity.ok(locationData);
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

//    @PostMapping("/signIn")
//    public ResponseEntity<String> signIn(@RequestBody MSignInResponse request) throws BaseException {
//        String signInService = buildingComtroller.signInService(request);
//        return ResponseEntity.ok(signInService);
//    }
//
//    @PostMapping("/test")
//    public ResponseEntity<Object> profileResponse() throws BaseException {
//        String userId = SecurityUtil.getCurrentUserId().get();
//        System.out.print("token id : "+userId);
//        //UserProfile testRess = authController.testRes1(request);
//        return ResponseEntity.ok(userId);
//    }
}
