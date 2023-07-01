package com.ellisonalves.thehotel.infrastructure.jpa.entity;

import java.time.LocalDate;

//@Entity
public class Booking {
    
  //  @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   // @ManyToOne
    private GuestJpa guest;
    
   // @ManyToOne
    private RoomJpa room;
    
    private LocalDate startDate;
    
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuestJpa getGuest() {
        return guest;
    }

    public void setGuest(GuestJpa guest) {
        this.guest = guest;
    }

    public RoomJpa getRoom() {
        return room;
    }

    public void setRoom(RoomJpa room) {
        this.room = room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
}
