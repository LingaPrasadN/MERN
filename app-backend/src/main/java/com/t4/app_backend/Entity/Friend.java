package com.t4.app_backend.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Table(name = "friends")
@Data
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user1Id;

    @Column(nullable = false)
    private Long user2Id;

    @Column
    private LocalDateTime createdAt;

}