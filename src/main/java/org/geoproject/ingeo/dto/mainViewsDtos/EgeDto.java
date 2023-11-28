package org.geoproject.ingeo.dto.mainViewsDtos;

import org.geoproject.ingeo.enums.dtoenums.EgeDTOFieldsEnum;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EgeDto {

    //Номер ИГЭ
    private String number;

    private int codeNumber;

    //описание почвы, вводится вручную
    //todo переназвать
    private String shortName;

    //Связь с сущностью Genesis по ID, при этом в представлении надо выводить в двух колонках код генезиса и описание
    private String genesisCode;

    private String genesisDescription;

    //ВА: SSA, enum - почва, пески, глинистые. От выбора зависят данные в меню "Вид грунта" и "Разновидность"
    private String soilKindEnum;

    //Описание для credo и формуляра. Вводится вручную. В поле может копировать информация из descriptionKga
    private String descriptionCredoFormular;

    //Описание по классификатору КГА. Вводится набором грунтов из классификатора
    private String descriptionKga;

    //Описание для камеральной задачи. Как правило, копируется из descriptionKga. Может быть ручной ввод.
    //Также в это поле должно попадать описание генезиса
    private String descriptionForOrganisation;

    //Штриховка, выбирается из классификатора
    private String hatchingNameCredoAutocad;

    //Консистенция, выбирается из классификатора
    private String consistency;

    //  todo сделать отдельную таблицу с цветами:
    private String color;


    private SoilClass soilClass;

    private SoilKind soilKind;



    public void setFieldValue(EgeDTOFieldsEnum field, Object value) {
        switch (field) {
            case NUMBER:
                number = (String) value;
                break;

            case CODE_NUMBER:
                codeNumber = (Integer) value;
                break;

            case SHORT_NAME:
                shortName = (String) value;
                break;

            case GENESIS_CODE:
                genesisCode = (String) value;
                break;

            case GENESIS_DESCRIPTION:
                genesisDescription = (String) value;
                break;

            case SOIL_KIND_ENUM:
                soilKindEnum = (String) value;
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

            case HATCHING_NAME_CREDO_AUTOCAD:
                hatchingNameCredoAutocad = (String) value;
                break;

            case CONSISTENCY:
                consistency = (String) value;
                break;

            case COLOR:
                color = (String) value;
                break;

            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
}
