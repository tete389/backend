package com.rmuti.guidemap.backend.table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "building_data")
public class BuildingData extends BaseEntity{

    @Column(name = "bu_title", unique = true, nullable = false, length = 60)
    private String title;

    @Column(name = "bu_address", nullable = false)
    private String address;

    @Column(name = "bu_latitude", nullable = false)
    private double latitude;

    @Column(name = "bu_longitude", nullable = false)
    private double longitude;

    @Column(name = "bu_image", nullable = false)
    private File image;

    @Column(name = "bu_detail", nullable = false)
    private String detail;

    @OneToMany
    @JoinColumn(name = "branch_id",nullable = false)
    private List<Branch> branch;

//    @OneToOne(mappedBy = "building",orphanRemoval = true)
//    private ChatRoom chatRoom;

}
