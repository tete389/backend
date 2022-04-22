package com.rmuti.guidemap.backend.table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "branch")
public class Branch extends BaseEntity{

    @Column(name = "br_name", unique = true, nullable = false, length = 60)
    private String title;

    @OneToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "branch")
    private List<BuildingData> building;

}
