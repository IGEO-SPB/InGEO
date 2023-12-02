package org.geoproject.ingeo.mapper.qualifier;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.classificators.kga.SoilGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.geoproject.ingeo.repositories.classificators.kga.SoilGroupTypeRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindAdjRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Named("EgeMapperQualifier")
@RequiredArgsConstructor
public class EgeMapperQualifier {

    private final SoilSubkindRepository soilSubkindRepository;
    private final SoilSubkindAdjRepository soilSubkindAdjRepository;

    @Named("getSoilSubkindById")
    public SoilSubkind getSoilSubkindById(Long id) {

//        var soilSubkind = soilSubkindRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SOIL_SUBKIND_NOT_FOUND_EXCEPTION.getMessage()));

        var soilSubkind = soilSubkindRepository.findById(id);

        return soilSubkind.orElse(null);
    }

    @Named("getSoilSubkindAdjById")
    public SoilSubkindAdj getSoilSubkindAdjById(Long id) {

        //TODO: заменить ошибку
//        var soilSubkindAdj = soilSubkindAdjRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SOIL_SUBKIND_NOT_FOUND_EXCEPTION.getMessage()));

        var soilSubkindAdj = soilSubkindAdjRepository.findById(id);

        return soilSubkindAdj.orElse(null);
    }
}
