package com.rmuti.guidemap.backend.repository;

import com.rmuti.guidemap.backend.table.UserData;
import com.rmuti.guidemap.backend.table.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    //Optional<UserProfile> findByUserData(UserData userData);

    @Query(value = "SELECT u.id FROM user_profile u WHERE fk_user_data_id = :userid",nativeQuery = true)
    Optional<String> findIdProfile(String userid);

}
