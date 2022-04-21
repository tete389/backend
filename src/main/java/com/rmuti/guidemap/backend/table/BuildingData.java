//package com.rmuti.guidemap.backend.table;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import javax.persistence.*;
//
//@EqualsAndHashCode(callSuper = true)
//@Data
//@Entity
//@Table(name = "building_data")
//public class BuildingData extends BaseEntity{
//
//    @Column(name = "bu_title", unique = true, nullable = false, length = 60)
//    private String title;
//
//    @Column(name = "bu_detail", nullable = false)
//    private String detail;
//
//    @OneToOne
//    @JoinColumn(name = "location_data_id",nullable = false)
//    private LocationData location;
//
////    @OneToMany(mappedBy = "building",orphanRemoval = true)
////    private ChatData chatData;
//
//}
