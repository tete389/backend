package com.rmuti.guidemap.backend.table;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_data")
public class UserData extends BaseEntity{

    @Column(name = "u_email", unique = true, nullable = false, length = 60)
    private String email;

    @Column(name = "u_password", nullable = false, length = 60)
    private String password;

//    @Column(name = "u_role", nullable = false, length = 60)
//    private String role;

    @OneToOne(mappedBy = "user",orphanRemoval = true)
    private UserProfile userProfile;
}
