package org.geoproject.ingeo.enums.laborenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Getter
@RequiredArgsConstructor
public enum WaterOdorEnum {

    ODORLESS("без запаха"),
    EMPTY(StringUtils.EMPTY);

    private final String odor;
}
