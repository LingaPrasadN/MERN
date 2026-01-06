package com.t4.app_backend.Service;

import com.t4.app_backend.DTO.InviteDTO;
import com.t4.app_backend.Entity.CustomUserDetails;
import com.t4.app_backend.Entity.Invite;
import com.t4.app_backend.Repository.InviteRepository;
import com.t4.app_backend.Repository.UserRepository;
import com.t4.app_backend.enums.InviteStatus;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendService friendService;

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

    public Invite acceptInvite(Long inviteId) {
        Invite invite = inviteRepository.findById(inviteId).get();
        invite.setStatus(InviteStatus.ACCEPTED);
        Long userId1 = userRepository.findByEmail(invite.getInviterEmail()).getId();
        Long userId2 = userRepository.findByEmail(invite.getInviteeEmail()).getId();
        friendService.addFriend(userId1, userId2);
        return inviteRepository.save(invite);
    }

    public Invite rejectInvite(Long inviteId) {
        Invite invite = inviteRepository.findById(inviteId).get();
        invite.setStatus(InviteStatus.REJECTED);
        return inviteRepository.save(invite);

    }

}
