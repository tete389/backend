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
//@Table(name = "location_data")
//public class LocationData extends BaseEntity{
//
//    @Column(name = "lo_address", nullable = false)
//    private double address;
//
//    @Column(name = "lo_longitude", nullable = false)
//    private double longitude;
//
//    @Column(name = "lo_latitude", nullable = false)
//    private double latitude;
//
//    @OneToOne(mappedBy = "location",orphanRemoval = true)
//    private BuildingData buildingData;
//
//    @OneToMany(mappedBy = "location",orphanRemoval = true)
//    private FacultyData facultyData;
//
//    @OneToMany(mappedBy = "location",orphanRemoval = true)
//    private BranchData branchData;
//
//
//}
