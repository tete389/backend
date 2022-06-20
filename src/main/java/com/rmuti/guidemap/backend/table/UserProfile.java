package com.rmuti.guidemap.backend.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "up_id", length = 36, nullable = false, updatable = false, unique = true)
    private String upId;

    @Column(name = "up_name", nullable = false, length = 60)
    private String upName;

    @Column(name = "up_status")
    private String upStatus;

    @Column(name = "up_image")
    private String upImage;

//    @JsonIgnore
//    @OneToOne(mappedBy = "userProfile", orphanRemoval = true, cascade = CascadeType.ALL)
//    private UserData userData;

//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_user_data")
//    private UserData userData;

//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "profile_data",
//            joinColumns = @JoinColumn(name = "userProfile_id", referencedColumnName = "Id"),
//            inverseJoinColumns = @JoinColumn(name = "userData_id", referencedColumnName = "Id"))
//    private UserData userData;


//    @JsonIgnore
//    @ManyToMany(mappedBy = "userProfile" , cascade = CascadeType.ALL)
//    private List<ChatRoom> chatRoom = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "userProfile" , cascade = CascadeType.ALL)
//    private List<ChatMessage> chatMessages = new ArrayList<>();
}
