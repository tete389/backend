package com.rmuti.guidemap.backend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.File;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_profile")
public class UserProfile extends BaseEntity{

    @Column(name = "u_name", nullable = false, length = 60)
    private String name;

    @Column(name = "u_status")
    private String status;

    @Column(name = "u_image")
    private File image;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_data_id")
    private UserData userData;
}
