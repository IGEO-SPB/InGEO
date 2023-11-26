package com.geoproject.igeo.models.classificators.kga;

import com.geoproject.igeo.models.Ege;
import com.geoproject.igeo.models.Project;
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

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "CL_SOIL_CLASS")
public class SoilClass {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SC_NAME")
    private String scName;

    @Column(name = "SC_ENABLE")
    private Integer scEnable;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATOR")
    private String creator;

    @Column(name = "DATE_CR")
    private String dateCr;

    @Column(name = "ARCHIVER")
    private String archiver;

    @Column(name = "DATE_ARCH")
    private String dateArch;

    @OneToMany
    @JoinColumn(name = "SC_ID")
    private List<SoilKind> soilKindList;

    @OneToMany
    @JoinColumn(name = "soil_class_id")
    private List<Ege> egeList;

}
