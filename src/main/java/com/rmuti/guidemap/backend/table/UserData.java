package com.rmuti.guidemap.backend.table;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;


//@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "ud_id", length = 36, nullable = false, updatable = false, unique = true)
    private String udId;

    @Column(name = "ud_email", unique = true, nullable = false, length = 60)
    private String udEmail;

    @Column(name = "ud_google_email", unique = true, nullable = false, length = 60)
    private String udGoogleEmail;

    @Column(name = "ud_password", nullable = false, length = 60)
    private String udPassword;

    @Column(name = "ud_role")
    private String udRole;

    @Column(name = "ud_user_profile_id")
    private String udUserProfileId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_user_profile", referencedColumnName = "id")
//    private UserProfile userProfile;


//    @OneToOne(mappedBy = "userData",orphanRemoval = true)
//    private UserProfile userProfile;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "Profile_data",
//            joinColumns = {@JoinColumn(name = "userData_id", referencedColumnName = "Id")},
//            inverseJoinColumns = {@JoinColumn(name = "userProfile_id", referencedColumnName = "Id")}
//    )

//    @JsonIgnore
//    @OneToOne(mappedBy = "userData" , orphanRemoval = true)
//    private UserProfile userProfile;
}
