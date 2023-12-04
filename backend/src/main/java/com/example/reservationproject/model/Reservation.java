package com.example.reservationproject.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Reservation() {
    }

    public Reservation(String title, LocalDateTime startAt, LocalDateTime endAt, Long roomId) {
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.room =new Room(roomId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
