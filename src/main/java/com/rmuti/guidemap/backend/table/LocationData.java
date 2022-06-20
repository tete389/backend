package com.rmuti.guidemap.backend.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "location_data")
public class LocationData {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "ld_id", length = 36, nullable = false, updatable = false, unique = true)
    private String ldId;

    @Column(name = "ld_name", unique = true, nullable = false, length = 60)
    private String ldName;

    @Column(name = "ld_address", nullable = false)
    private String ldAddress;

    @Column(name = "ld_latitude", nullable = false)
    private double ldLatitude;

    @Column(name = "ld_longitude", nullable = false)
    private double ldLongitude;

    @Column(name = "ld_image")
    private String ldImage;

    @Column(name = "ld_detail")
    private String ldDetail;

    //@JsonIgnore
//    @OneToOne(mappedBy = "locationId",orphanRemoval = true)
//    private ChatRoom chatRoom;

}
