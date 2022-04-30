package com.rmuti.guidemap.backend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "location")
public class Location extends BaseEntity{

    @Column(name = "l_name", unique = true, nullable = false, length = 60)
    private String name;

    @Column(name = "l_address", nullable = false)
    private String address;

    @Column(name = "l_latitude", nullable = false)
    private double latitude;

    @Column(name = "l_longitude", nullable = false)
    private double longitude;

    @Column(name = "l_image")
    private File image;

    @Column(name = "l_detail")
    private String detail;

}
