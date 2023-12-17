package org.geoproject.ingeo.models;

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
import org.geoproject.ingeo.models.classificators.SurveyPointsType;
import org.geoproject.ingeo.models.labor.WaterSampleResult;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "survey_point")
public class SurveyPoint {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //В рамках какого проекта
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    //тип точки исследования
    @ManyToOne
    @JoinColumn(name = "survey_point_type_id", referencedColumnName = "id")
    private SurveyPointsType surveyPointsType;

    @Column(name = "simple")
    private Boolean simple;

    //Номер точки исследования в рамках проекта
    @Column(name = "point_number")
    private String pointNumber;

    //Глубина бурения todo точно так?
    @Column(name = "depth")
    private Float depth;

    @Column(name = "depth_full_old_borehole")
    private Float depthFullOldBorehole;

    //Абсолютная отметка
    @Column(name = "absolute_mark")
    private Float absoluteMark;

    @Column(name = "absolute_mark_archive")
    private Float absoluteMarkArchive;

    @Column(name = "coordinate_x")
    private Float coordinateX;

    @Column(name = "coordinate_y")
    private Float coordinateY;

    //Дата начала проходки
    @Column(name = "start_date")
    private String startDate;

    //Дата окончания проходки
    @Column(name = "end_date")
    private String endDate;

    //Пробурено организацией
    @Column(name = "executor_company")
    private String executorCompany;

    @Column(name = "list")
    private String list;

    //Масштаб чертежа
    @Column(name = "mas")
    private Integer mas;

    @Column(name = "tablet_nomenclature")
    private String tabletNomenclature;

    //Диаметр скважины
    @Column(name = "borehole_diameter")
    private Float boreholeDiameter;

    //Вид бурения
    @Column(name = "boring_type")
    private String boringType;

    //Буровой агрегат
    @Column(name = "drilling_unit_type")
    private String drillingUnitType;

    //статус выработки
    @Column(name = "i_NFC")
    private Integer iNFC;

    //столб воды
    @Column(name = "f_Aq")
    private Integer fAq;

    @Column(name = "location_number")
    private String locationNumber;

    @Column(name = "location")
    private String location;

    //SKV_NAME, имя + номер скважины
    @Column(name = "name_number")
    private String nameNumber;

    //Примечание в колонку, в заголовке чертежа колонки
    @Column(name = "point_comment")
    private String pointComment;

    @OneToMany(mappedBy = "surveyPoint")
    private List<BoreholeLayer> boreholeLayerList;

    @OneToMany(mappedBy = "surveyPoint")
    private List<Sample> sampleList;

    @OneToMany(mappedBy = "surveyPoint", fetch = FetchType.EAGER)
    private List<WaterSampleResult> waterSampleResultList;

    public SurveyPoint(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SurveyPoint{" +
                "id=" + id +
                ", project=" + project +
                ", surveyPointsType=" + surveyPointsType +
                ", pointNumber='" + pointNumber + '\'' +
                '}';
    }
}
