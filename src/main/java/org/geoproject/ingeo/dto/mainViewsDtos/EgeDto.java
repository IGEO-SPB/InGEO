package org.geoproject.ingeo.dto.mainViewsDtos;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.enums.dtoenums.EgeDTOFieldsEnum;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.classificators.Genesis;
import org.geoproject.ingeo.models.classificators.kga.Color;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EgeDto {

    private Long id;

    //Номер ИГЭ
    private String number;

    //описание почвы, вводится вручную
    private String shortName;

    //Связь с сущностью Genesis по ID, при этом в представлении надо выводить в двух колонках код генезиса и описание
    private GenesisDto genesisDto;

    //Описание для credo и формуляра. Вводится вручную. В поле может копироваться информация из descriptionKga
    private String descriptionCredoFormular;

    //Описание по классификатору КГА. Вводится набором грунтов из классификатора
    private String descriptionKga;

    //Описание для камеральной задачи. Как правило, копируется из descriptionKga. Может быть ручной ввод.
    //Также в это поле должно попадать описание генезиса
    private String descriptionForOrganisation;

    //Штриховка, выбирается из классификатора
    private HatchingDto hatchingDto;

    //Консистенция, выбирается из классификатора
    private ConsistencyDto consistencyDto;


    public void setFieldValue(EgeDTOFieldsEnum field, Object value) {
        switch (field) {
            case NUMBER:
                number = (String) value;
                break;

            case SHORT_NAME:
                shortName = (String) value;
                break;

            case GENESIS:
                genesisDto = (GenesisDto) value;
                break;

            case DESCRIPTION_CREDO_FORMULAR:
                descriptionCredoFormular = (String) value;
                break;

            case DESCRIPTION_KGA:
                descriptionKga = (String) value;
                break;

            case DESCRIPTION_FOR_ORGANISATION:
                descriptionForOrganisation = (String) value;
                break;

//            case HATCHING_NAME_CREDO_AUTOCAD:
//                hatchingNameCredoAutocad = (String) value;
//                break;
//
//            case CONSISTENCY:
//                consistency = (String) value;
//                break;

            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
}
