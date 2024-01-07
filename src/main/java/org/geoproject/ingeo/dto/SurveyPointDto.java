package org.geoproject.ingeo.dto;

import org.geoproject.ingeo.models.BoreholeLayer;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.classificators.SurveyPointsType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class SurveyPointDto {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyPointDto that = (SurveyPointDto) o;
        return Objects.equals(project, that.project) && Objects.equals(pointNumber, that.pointNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, pointNumber);
    }
}
