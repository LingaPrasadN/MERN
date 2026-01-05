package com.t4.app_backend.Service;

import com.t4.app_backend.Entity.Friend;
import com.t4.app_backend.Repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public void addFriend(Long userA, Long userB) {

        if (userA.equals(userB)) {
            throw new RuntimeException("Cannot friend yourself");
        }

        Long min = Math.min(userA, userB);
        Long max = Math.max(userA, userB);

        if (friendRepository.existsByUser1IdAndUser2Id(min, max)) {
            return;
        }

        Friend friend = new Friend();
        friend.setUser1Id(userA);
        friend.setUser2Id(userB);
        friend.setCreatedAt(LocalDateTime.now());
        friendRepository.save(friend);
    }

}
