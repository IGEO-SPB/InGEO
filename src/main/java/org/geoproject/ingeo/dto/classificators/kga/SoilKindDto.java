package org.geoproject.ingeo.dto.classificators.kga;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.geoproject.ingeo.enums.dtoenums.classificators.SoilDtoFieldsEnum;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;

import java.util.List;

@Getter
@Setter
public class SoilKindDto {

    private Long id;
    private String soilClassName;
    private String skGroup;
    private String skSubgroupOne;
    private String skSubgroupTwo;
    private String skTypeOne;
    private String skTypeTwo;
    private String skKind;

//    public void setFieldValue(SoilDtoFieldsEnum field, Object value) {
//        switch (field) {
//            case ID -> id = (Long) value;
//            case SOIL_CLASS -> soilClass = (SoilClass) value;
//            case SK_GROUP -> skGroup = (String) value;
//            case SK_SUBGROUP_ONE -> skSubgroupOne = (String) value;
//            case SK_SUBGROUP_TWO -> skSubgroupTwo = (String) value;
//            case SK_TYPE_ONE -> skTypeOne = (String) value;
//            case SK_TYPE_TWO -> skTypeTwo = (String) value;
//            case SK_KIND -> skKind = (String) value;
//            default -> throw new IllegalArgumentException("Invalid field: " + field);
//        }
//    }

}
