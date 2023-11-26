package org.geoproject.ingeo.dto.classificators;

import org.geoproject.ingeo.enums.dtoenums.classificators.WeighingBottleDtoFieldsEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class WeighingBottleDto extends AbstractClassificator {

    private String number;
    private Float weight;
    private Boolean isActive;

    public void setFieldValue(WeighingBottleDtoFieldsEnum field, Object value) {
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
        WeighingBottleDto that = (WeighingBottleDto) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
