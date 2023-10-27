package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.entities.utils.EmployeeSkill;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "skills")
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @OneToMany(mappedBy = "employee")
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
}
