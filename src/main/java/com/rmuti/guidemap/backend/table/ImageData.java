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
@Table(name = "image_data")
public class ImageData {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "img_id", length = 36, nullable = false, updatable = false, unique = true)
    private String imgId;

    @Column(name = "img_path", nullable = false, updatable = false)
    private String imgPath;

    @Column(name = "img_name",nullable = false, updatable = false)
    private String imgName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "img_create")
    private Date imgCreate;

    public ImageData() {
        imgCreate = new Date();
    }

}
