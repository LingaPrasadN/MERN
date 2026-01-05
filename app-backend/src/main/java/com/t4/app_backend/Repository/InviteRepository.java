package com.t4.app_backend.Repository;

import com.t4.app_backend.Entity.Invite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {

    List<Invite> findAllByInviterEmail(String loggedInUserId);

    List<Invite> findAllByInviteeEmail(String loggedInUserId);

}
