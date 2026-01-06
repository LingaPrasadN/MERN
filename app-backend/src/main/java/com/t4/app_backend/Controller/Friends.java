package com.t4.app_backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.t4.app_backend.Entity.CustomUserDetails;
import com.t4.app_backend.Entity.User;
import com.t4.app_backend.Service.FriendService;

@RestController
@RequestMapping("/friends")
public class Friends {

    @Autowired
    private FriendService friendService;
    
    @GetMapping("/get")
    public List<User> getFriends(@AuthenticationPrincipal CustomUserDetails user) {
        return friendService.getFriends(user);

    }


}
