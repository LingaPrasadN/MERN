package com.t4.app_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import com.t4.app_backend.Service.InviteService;
import com.t4.app_backend.DTO.InviteDTO;
import com.t4.app_backend.Entity.CustomUserDetails;
import com.t4.app_backend.Entity.Invite;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/invites")
public class Invites {

    @Autowired
    private InviteService inviteService;

    @PostMapping("/send")
    public void sendInvite(@AuthenticationPrincipal CustomUserDetails user, @RequestBody InviteDTO inviteDTO) {
        
        inviteService.sendInvite(user, inviteDTO);
    }

    @GetMapping("/sent/get")
    public Object getSentInvites(@AuthenticationPrincipal CustomUserDetails user) {
        return inviteService.getSentInvites(user);
    }

    @GetMapping("/received")
    public Object getReceivedInvites(@AuthenticationPrincipal CustomUserDetails user) {
        return inviteService.getReceivedInvites(user);
    }
    
    

}
