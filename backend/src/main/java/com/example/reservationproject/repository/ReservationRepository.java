package com.example.reservationproject.repository;

import com.example.reservationproject.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomId(Long roomId);

    @Query("SELECT r FROM Reservation r WHERE ((:startAt BETWEEN r.startAt AND r.endAt) OR (:endAt BETWEEN r.startAt AND r.endAt)) AND (:roomId = r.room.id)")
    List<Reservation> findByReservationDateRange(@Param("startAt") LocalDateTime startAt, @Param("endAt") LocalDateTime endAt, @Param("roomId") Long roomId);
}

