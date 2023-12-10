package com.example.reservationproject;

import com.example.reservationproject.model.Room;
import com.example.reservationproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ReservationProjectApplication implements CommandLineRunner {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(ReservationProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (isDevProfileActive()) {
            createRooms();
        }
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

    private boolean isDevProfileActive() {
        for (String profile : environment.getActiveProfiles()) {
            if (profile.equals("dev")) {
                return true;
            }
        }
        return false;
    }
}
