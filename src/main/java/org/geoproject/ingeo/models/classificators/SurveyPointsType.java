package org.geoproject.ingeo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "classif_survey_points_type")
public class SurveyPointsType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "i_bcd_number")
    private String iBcdNumber;

    @Column(name = "survey_type_short_name")
    private String surveyTypeShortName;

    @Column(name = "survey_type_full_name")
    private String surveyTypeFullName;

    @Column(name = "additional_info")
    private String additionalInfo;

    @OneToMany(mappedBy = "surveyPointsType")
    private List<SurveyPoint> points;
}
