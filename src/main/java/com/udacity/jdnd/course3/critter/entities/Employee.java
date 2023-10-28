package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.entities.enums.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "emp_skills",
            joinColumns = @JoinColumn(name = "employee_id")
    )
    @Column(name = "skill")
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @CollectionTable(
            name = "emp_availability",
            joinColumns = @JoinColumn(name = "employee_id")
    )
    @Column(name = "available_day")
    private Set<DayOfWeek> daysAvailable;

    @OneToMany(
            mappedBy = "employee",
            fetch = FetchType.LAZY
    )
    private Set<Schedule> schedules;

    public Employee() {
    }

    public Employee(String name, Set<EmployeeSkill> skills) {
        this.name = name;
        this.skills = skills;
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

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
