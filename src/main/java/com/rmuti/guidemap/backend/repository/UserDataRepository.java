package com.rmuti.guidemap.backend.repository;

import java.util.Optional;

import com.rmuti.guidemap.backend.table.UserData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDataRepository extends JpaRepository<UserData, String> {

    Optional<UserData> findByUdEmail(String email);

    Optional<UserData> findByUdGoogleEmail(String email);

    @Query(value = "SELECT ud.*, up.up_name, up.up_status, up.up_image FROM " +
            "user_data ud JOIN user_profile up ON ud.ud_userProfile_id = :userProfile_id", nativeQuery = true)
    Optional<UserDataResponse> findByUserProfileId(@Param("userProfile_id")String profile);

    boolean existsByUdEmail(String email);

    boolean existsByUdGoogleEmail(String email);

    public static interface UserDataResponse{
        String getUd_Id();

        String getUd_Email();

        String getUd_Password();

        String getUd_Role();

        String getUd_UserProfileId();

        String getUp_Name();

        String getUp_Status();

        String getUp_Image();
    }
}
