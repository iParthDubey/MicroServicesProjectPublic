package com.abc.hotel.HotelService.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getAllStaffMembers(){
        List<String> list = Arrays.asList("Abdul","Mohammad","Raju");
        return ResponseEntity.ok(list);
    }
}
