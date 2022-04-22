package com.rmuti.guidemap.backend.repository;

import com.rmuti.guidemap.backend.table.UserData;
import com.rmuti.guidemap.backend.table.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    Optional<UserProfile> findByUserData(UserData userData);

}
