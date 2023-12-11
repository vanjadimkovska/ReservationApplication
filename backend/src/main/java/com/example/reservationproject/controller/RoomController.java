package com.example.reservationproject.controller;

import com.example.reservationproject.model.Room;
import com.example.reservationproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/getAll")
    public List<Room> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }
}
