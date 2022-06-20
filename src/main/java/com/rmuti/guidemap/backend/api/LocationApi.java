package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.business.ImageDataBusiness;
import com.rmuti.guidemap.backend.business.LocationDataBusiness;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.models.MLocationRequest;
import com.rmuti.guidemap.backend.models.MLocationResponse;
import com.rmuti.guidemap.backend.table.LocationData;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/location")
public class LocationApi {

    //
    private final LocationDataBusiness locationBusiness;

    private final ImageDataBusiness imageDataBusiness;


    //
    @PostMapping("/createLocation")
    public ResponseEntity<String> createLocation(@RequestBody LocationData request) throws BaseException {
        String locationData = locationBusiness.createLocation(request);
        return ResponseEntity.ok(locationData);
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @GetMapping("/getLocation")
    public ResponseEntity<MLocationResponse> getLocation() throws BaseException {
        MLocationResponse res = locationBusiness.getLocation();
        return ResponseEntity.ok(res);
    }


    @PostMapping("/updateLocation")
    public ResponseEntity<String> updateLocation(@RequestBody LocationData request) throws BaseException {
        String locationData = locationBusiness.updateLocation(request);
        return ResponseEntity.ok(locationData);
    }

    @PostMapping("/uploadLocationImage")
    public ResponseEntity<String> uploadLocationImage(@RequestPart MultipartFile file, @RequestPart LocationData request) throws BaseException, IOException {
        String response = imageDataBusiness.uploadImageLocation(file , request);
        return ResponseEntity.ok(response);
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

}
