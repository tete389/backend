package com.rmuti.guidemap.backend.table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "faculty")
public class Faculty extends BaseEntity{

    @Column(name = "fa_name", unique = true, nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy = "faculty")
    private List<Branch> branch;
}
