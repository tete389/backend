package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.models.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Api {

    @GetMapping
    public Response test() {
        Response response = new Response();
        response.setName("test");

        return response;
    }
}
