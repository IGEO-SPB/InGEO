package org.geoproject.ingeo.dto.mainViewsDtos;

import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.enums.dtoenums.EgeDTOFieldsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EgeDto {

    private Long id;

    /**
     * Техническое поле для сортировки
     */
    private Integer codeNumber;

    //Номер ИГЭ
    private String egeNumber;

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
            case EGE_NUMBER:
                egeNumber = (String) value;
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

            case HATCHING:
                hatchingDto = (HatchingDto) value;
                break;

            case CONSISTENCY:
                consistencyDto = (ConsistencyDto) value;
                break;

            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
}
