package com.example.reservationproject.repository;

import com.example.reservationproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByName(String name);
}