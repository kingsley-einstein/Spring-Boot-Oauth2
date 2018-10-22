package com.security.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.security.practice.models.ProfilePic;

@Repository
public interface ProfilePicRepository extends JpaRepository<ProfilePic, Long> {
    
}