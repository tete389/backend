package com.rmuti.guidemap.backend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

//@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "cm_id", length = 36, nullable = false, updatable = false, unique = true)
    private String cmId;

    @Column(name = "cm_message", nullable = false)
    private String cmMessage;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "cm_created", nullable = false)
    private Date cmCreated;

    @Column(name = "cm_status", nullable = false)
    private boolean cmStatus;

    @Column(name = "cm_type_message", nullable = false)
    private String cmTypeMessage;

    @Column(name = "cm_image", nullable = false)
    private String cmImage;

    @Column(name = "cm_user_profile_id", nullable = false)
    private String cmUserProfileId;

    @Column(name = "cm_chat_room_id", nullable = false)
    private String cmChatRoomId;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_chatMessage",
//            joinColumns = @JoinColumn(name = "chatMessage_id"),
//            inverseJoinColumns = @JoinColumn(name = "userProfile_id")
//    )
//    private UserProfile userProfile;



    public ChatMessage() {
        cmCreated = new Date();
    }

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_chat_room_id")
//    private  ChatRoom chatRoom;
}
