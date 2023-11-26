package org.geoproject.ingeo.dto.classificators;

import org.geoproject.ingeo.enums.dtoenums.classificators.RingDtoFieldsEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class RingDto extends AbstractClassificator {

    private String number;
    private Integer d1;
    private Integer d2;
    private Float height;
    private Float volume;
    private Float weight;
    private Boolean isActive;

    public void setFieldValue(RingDtoFieldsEnum field, Object value) {
        switch (field) {
            case NUMBER -> number = (String) value;
            case D1 -> d1 = (Integer) value;
            case D2 -> d2 = (Integer) value;
            case HEIGHT -> height = (Float) value;
            case VOLUME -> volume = (Float) value;
            case WEIGHT -> weight = (Float) value;
            case IS_ACTIVE -> isActive = (Boolean) value;
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RingDto ringDto = (RingDto) o;
        return Objects.equals(number, ringDto.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
