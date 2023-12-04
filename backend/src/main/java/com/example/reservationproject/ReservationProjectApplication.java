package com.example.reservationproject;

import com.example.reservationproject.model.Room;
import com.example.reservationproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationProjectApplication implements CommandLineRunner {

    @Autowired
    private RoomRepository roomRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReservationProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createRooms();
    }

    private void createRooms() {
        createRoomIfNotExists("Room 1");
        createRoomIfNotExists("Room 2");
    }

    private void createRoomIfNotExists(String name) {
        if (roomRepository.findByName(name) == null) {
            Room room = new Room();
            room.setName(name);
            roomRepository.save(room);
        }
    }

}
