package com.t4.app_backend.Service;

import com.t4.app_backend.DTO.InviteDTO;
import com.t4.app_backend.Entity.CustomUserDetails;
import com.t4.app_backend.Entity.Invite;
import com.t4.app_backend.Repository.InviteRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private EmailService emailService;

    public void sendInvite(CustomUserDetails user, InviteDTO inviteDTO) {

        String loggedInUserId = user.getUsername();

        Invite invite = new Invite();
        invite.setInviterEmail(loggedInUserId);
        invite.setInviteeEmail(inviteDTO.getToEmail());
        invite.setSentAt(LocalDateTime.now());
        // emailService.sendInviteEmail(loggedInUserId, inviteDTO.getToEmail());
        inviteRepository.save(invite);

    }

    public List<Invite> getSentInvites(CustomUserDetails user) {

        String loggedInUserId = user.getUsername();

        return inviteRepository.findAllByInviterEmail(loggedInUserId);

    }

    public List<Invite> getReceivedInvites(CustomUserDetails user) {
        String loggedInUserId = user.getUsername();
        return inviteRepository.findAllByInviteeEmail(loggedInUserId);

    }

}
