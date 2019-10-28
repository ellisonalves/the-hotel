package com.ellisonalves.thehotel.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Guest guest;
    @ManyToOne
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
}
