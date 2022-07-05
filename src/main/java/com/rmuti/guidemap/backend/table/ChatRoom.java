package com.rmuti.guidemap.backend.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "cr_id", length = 36, nullable = false, updatable = false, unique = true)
    private String crId;

    @Column(name = "cr_name", unique = true, nullable = false, length = 60)
    private String crName;

    @Column(name = "cr_image")
    private String crImage;

    @Column(name = "cr_location_id")
    private String crLocationId;

//    @Column(name = "cr_userProfile")
//    private List<String> userProfile = new ArrayList<>();


    //@JsonIgnore

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_chatRooms",
//            joinColumns = @JoinColumn(name = "chatRoom_id"),
//            inverseJoinColumns = @JoinColumn(name = "userProfile_id")
//             )
//    private List<UserProfile> userProfile = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL)
//    private ChatMessage chatMessage;

}
