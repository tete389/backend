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
//@Table(name = "faculty_data")
//public class FacultyData extends BaseEntity{
//
//    @Column(name = "fa_title", unique = true, nullable = false, length = 60)
//    private String title;
//
//    @Column(name = "fa_detail", nullable = false)
//    private String detail;
//
//    @ManyToOne
//    @JoinColumn(name = "location_data_id",nullable = false)
//    private LocationData location;
//}
