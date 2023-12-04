package com.example.reservationproject.controller;

import com.example.reservationproject.model.Reservation;
import com.example.reservationproject.model.Room;
import com.example.reservationproject.repository.ReservationRepository;
import com.example.reservationproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/getAllRooms")
    public List<Room> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }
}
