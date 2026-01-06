package com.t4.app_backend.Service;

import com.t4.app_backend.Entity.CustomUserDetails;
import com.t4.app_backend.Entity.Friend;
import com.t4.app_backend.Entity.User;
import com.t4.app_backend.Repository.FriendRepository;
import com.t4.app_backend.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    public void addFriend(Long userA, Long userB) {

        if (userA.equals(userB)) {
            throw new RuntimeException("Cannot friend yourself");
        }

        Long min = Math.min(userA, userB);
        Long max = Math.max(userA, userB);
        if (friendRepository.existsByUser1IdAndUser2Id(min, max) != null) {
            return;
        }

        Friend friend = new Friend();
        friend.setUser1Id(min);
        friend.setUser2Id(max);
        friend.setCreatedAt(LocalDateTime.now());
        friendRepository.save(friend);
    }

    public List<User> getFriends(CustomUserDetails user) {

        Long user1Id = userRepository.findByEmail(user.getUsername()).getId();
        System.out.println(user1Id);
        List<Friend> friends1 = friendRepository.findByUser1Id(user1Id);
        List<Friend> friends2 = friendRepository.findByUser2Id(user1Id);
        System.out.println(friends1.size());
        System.out.println(friends2.size());
        List<User> users = new ArrayList<>();

        for (Friend friend : friends1) {
            Long user2Id = friend.getUser2Id();
            users.add(userRepository.findById(user2Id).get());
        }

        for (Friend friend : friends2) {
            Long user2Id = friend.getUser1Id();
            users.add(userRepository.findById(user2Id).get());
        }

        System.out.println(users.size());
        System.out.println("-------------");

        return users;

    }

}
