package com.geoproject.igeo.dto.classificators;

import com.geoproject.igeo.enums.dtoenums.classificators.PotDtoFieldsEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class PotDto extends AbstractClassificator {

    private String number;
    private Float weight;
    private Boolean isActive;

    public void setFieldValue(PotDtoFieldsEnum field, Object value) {
        switch (field) {
            case NUMBER -> number = (String) value;
            case WEIGHT -> weight = (Float) value;
            case IS_ACTIVE -> isActive = (Boolean) value;
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PotDto potDto = (PotDto) o;
        return Objects.equals(number, potDto.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}