package com.geoproject.igeo.enums.laborenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum HCLStatusEnum {
    EMPTY("Тигли"),
    DETECTED("вск"),
    NOT_DETECTED("не вск"),
    ;

    private final String status;

    public static List<String> getStatuses() {
        List<String> statuses = new ArrayList<>();
        for (HCLStatusEnum status : HCLStatusEnum.values()) {
            statuses.add(status.getStatus());
        }
        return statuses;
    }
}
