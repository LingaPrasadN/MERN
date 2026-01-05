package com.t4.app_backend.Entity;

import java.time.LocalDateTime;

import com.t4.app_backend.enums.InviteStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder.In;

@Entity
@Table(name = "invites")
@Data
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String inviterEmail;

    @Column(nullable = false)
    private String inviteeEmail;

    @Enumerated(EnumType.STRING)
    private InviteStatus status = InviteStatus.PENDING;

    @Column
    private LocalDateTime sentAt;


}