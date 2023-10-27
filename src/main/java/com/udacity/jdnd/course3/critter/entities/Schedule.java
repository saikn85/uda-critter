package com.udacity.jdnd.course3.critter.entities;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
