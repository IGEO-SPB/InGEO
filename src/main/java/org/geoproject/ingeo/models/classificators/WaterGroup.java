package com.geoproject.igeo.models.classificators;

import com.geoproject.igeo.models.Project;
import com.geoproject.igeo.models.WaterSampleResult;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "classif_water_group")
public class WaterGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_number")
    private Integer groupNumber;

    @Column(name = "og_1")
    private String og1;

    @Column(name = "og_2")
    private String og2;

    @Column(name = "og_3")
    private String og3;

    @Column(name = "kf")
    private Float kf;

    @OneToMany
    @JoinColumn(name = "water_group_id")
    private Set<WaterSampleResult> waterSampleResults;

    public WaterGroup(Long id) {
        this.id = id;
    }
}
