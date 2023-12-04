package com.example.reservationproject;

import com.example.reservationproject.model.Reservation;
import com.example.reservationproject.model.Room;
import com.example.reservationproject.repository.ReservationRepository;
import com.example.reservationproject.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
class ReservationProjectApplicationTests {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    void testCreateAndRetrieveReservation() {
        Room room = new Room();
        room.setName("Test Room");
        room = roomRepository.save(room);

        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(2);

        Reservation reservation = new Reservation("Test Reservation", startAt, endAt, room.getId());
        reservation = reservationRepository.save(reservation);

        Reservation retrievedReservation = reservationRepository.findById(reservation.getId()).orElse(null);
        Assertions.assertNotNull(retrievedReservation);
        Assertions.assertEquals("Test Reservation", retrievedReservation.getTitle());
    }

    @Test
    void testFindReservationsByRoomId() {
        Room room = new Room();
        room.setName("Test Room");
        room = roomRepository.save(room);

        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(2);

        Reservation reservation1 = new Reservation("Reservation 1", startAt, endAt, room.getId());
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation("Reservation 2", startAt.plusDays(1), endAt.plusDays(1), room.getId());
        reservationRepository.save(reservation2);

        List<Reservation> reservations = reservationRepository.findByRoomId(room.getId());
        Assertions.assertEquals(2, reservations.size());
    }

    @Test
    void testCreateOverlappingReservation() {
        Room room = new Room();
        room.setName("Test Room");
        room = roomRepository.save(room);

        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(2);

        Reservation reservation1 = new Reservation("Reservation 1", startAt, endAt, room.getId());
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation("Reservation 2", startAt.plusMinutes(30), endAt.plusMinutes(30), room.getId());
        try {
            reservationRepository.save(reservation2);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("Overlaps"));
        }
    }
}
