package org.geoproject.ingeo.dto.mainViewsDtos;

import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.enums.dtoenums.EgeDtoFieldsEnum;
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


    public void setFieldValue(EgeDtoFieldsEnum field, Object value) {
        switch (field) {
            case EGE_NUMBER -> egeNumber = (String) value;
            case SHORT_NAME -> shortName = (String) value;
            case GENESIS -> genesisDto = (GenesisDto) value;
            case DESCRIPTION_CREDO_FORMULAR -> descriptionCredoFormular = (String) value;
            case DESCRIPTION_KGA -> descriptionKga = (String) value;
            case DESCRIPTION_FOR_ORGANISATION -> descriptionForOrganisation = (String) value;
            case HATCHING -> hatchingDto = (HatchingDto) value;
            case CONSISTENCY -> consistencyDto = (ConsistencyDto) value;
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
}
