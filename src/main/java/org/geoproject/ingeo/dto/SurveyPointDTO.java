package com.geoproject.igeo.dto;

import com.geoproject.igeo.models.BoreholeLayer;
import com.geoproject.igeo.models.Project;
import com.geoproject.igeo.models.Sample;
import com.geoproject.igeo.models.SurveyPointsType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyPointDTO {

    private Long id;
    private Project project;
    private SurveyPointsType surveyPointsType;
    private Boolean simple;
    private String pointNumber;
    private Float depth;
    private Float depthFullOldBorehole;
    private Float absoluteMark;
    private Float absoluteMarkArchive;
    private Float coordinateX;
    private Float coordinateY;
    private String startDate;
    private String endDate;
    private String executorCompany;
    private String list;
    private Integer mas;
    private String tabletNomenclature;
    private Float boreholeDiameter;
    private String boringType;
    private String drillingUnitType;
    private Integer iNFC;
    private Integer fAq;
    private String locationNumber;
    private String location;
    private String nameNumber;
    private String pointComment;
    private List<BoreholeLayer> boreholeLayerList;
    private List<Sample> sampleList;
}
