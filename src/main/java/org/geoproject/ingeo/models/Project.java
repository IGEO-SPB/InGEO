package org.geoproject.ingeo.models;

import org.geoproject.ingeo.models.classificators.ConstructionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
@RequiredArgsConstructor
public class Project {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "archive_number")
    private String archiveNumber;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "town")
    private String town;

    @Column(name = "street")
    private String street;

    @ManyToOne
    @JoinColumn(name = "construction_type")
    private ConstructionType constructionType;

    @Column(name = "report_name")
    private String reportName;

    @Column(name = "absolute_medium_winter_temperature")
    private Float absoluteMediumWinterTemperature;

    @ManyToOne
//    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    @ManyToOne
//    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "executor")
    private Employee executor;

    @ManyToOne
//    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "approver")
    private Employee approver;

    @Column(name = "assignment_date")
    private LocalDate assignmentDate;

    @Column(name = "projecting_stage")
    private String projectingStage;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    //TODO: Вероятно, потребуется для карты. Если нет - удалить.
    @Column(name = "map_point")
    private Integer mapPoint;

    @Column(name = "coordinate_x")
    private Float coordinateX;

    @Column(name = "coordinate_y")
    private Float coordinateY;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<SurveyPoint> surveyPoints;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<Ege> egeList;

    @Column(name = "is_archive")
    private Boolean isArchive;
}
