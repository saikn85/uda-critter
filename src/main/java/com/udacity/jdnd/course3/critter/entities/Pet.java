package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.entities.utils.PetType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PetType type;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "pet")
    private Set<Schedule> schedules;

    public Pet() {
    }

    public Pet(String name, PetType type, String notes, LocalDate birthDate) {
        this.name = name;
        this.type = type;
        this.notes = notes;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", notes='" + notes + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
