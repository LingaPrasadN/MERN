package com.t4.app_backend.Repository;

import com.t4.app_backend.Entity.Friend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("SELECT f.id id FROM Friend f WHERE f.user1Id = :min AND f.user2Id = :max")
    Long existsByUser1IdAndUser2Id(Long min, Long max);

    List<Friend> findByUser1Id(Long user1Id);

    List<Friend> findByUser2Id(Long user2Id);

}
