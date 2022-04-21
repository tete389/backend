//package com.rmuti.guidemap.backend.table;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@EqualsAndHashCode(callSuper = true)
//@Data
//@Entity
//@Table(name = "chat_data")
//public class ChatData extends BaseEntity{
//
//    @Column(name = "c_massage", nullable = false, length = 200)
//    private String massage;
//
//    @Column(name = "c_date", nullable = false)
//    private LocalDateTime date;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id",nullable = false)
//    private UserData user;
//
//    @ManyToOne
//    @JoinColumn(name = "building_id",nullable = false)
//    private BuildingData building;
//
//
//
//}
