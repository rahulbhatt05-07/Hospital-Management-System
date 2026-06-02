package com.learning.HospitalManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.HospitalManagementSystem.BloodGroupType;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@ToString
@Table(name="patient", uniqueConstraints = {
        @UniqueConstraint(name="unique_name_birthdate", columnNames = {"name","birthdate"})
})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 40)
    private String name;

    private LocalDate birthdate;

    @Column(nullable = false,length = 40)
    @Email
    private String email;

    private String gender;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade = {PERSIST}, orphanRemoval = true)
    private Insurance insurance;

    @OneToMany(mappedBy = "patient", cascade = REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private List<Appointment> appointment = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user; // Is patient ka login account

}

