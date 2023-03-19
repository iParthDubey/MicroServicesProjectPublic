package com.abc.hotel.HotelService.Controller;

import com.abc.hotel.HotelService.entities.Hotel;
import com.abc.hotel.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body((hotelService.create(hotel)));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelbyId(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body((hotelService.get(hotelId)));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll() {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.getAll());
    }
}
