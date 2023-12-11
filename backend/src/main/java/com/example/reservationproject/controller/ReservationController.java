package com.example.reservationproject.controller;

import com.example.reservationproject.model.Reservation;
import com.example.reservationproject.model.Room;
import com.example.reservationproject.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping("/create/{roomId}")
    public ResponseEntity<?> createReservation(@PathVariable Long roomId, @RequestBody Reservation reservation) {
        List<Reservation> reservations = reservationRepository.findByReservationDateRange(reservation.getStartAt(), reservation.getEndAt(), roomId);
        if(!reservations.isEmpty()) return ResponseEntity.status(400).body("The meeting you want to schedule overlaps with another meeting in that room. ");
        Room room = new Room(roomId);
        reservation.setRoom(room);

        return ResponseEntity.ok().body(reservationRepository.save(reservation));
    }

    @GetMapping("/getAll/{roomId}")
    public List<Reservation> getAllReservations(@PathVariable Long roomId) {
        return reservationRepository.findByRoomId(roomId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            reservationRepository.delete(optionalReservation.get());
            return ResponseEntity.ok("Reservation deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found.");
        }
    }

}
