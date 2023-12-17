package org.geoproject.ingeo.mapper.qualifier;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.models.classificators.Consistency;
import org.geoproject.ingeo.models.classificators.Genesis;
import org.geoproject.ingeo.models.classificators.Hatching;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.geoproject.ingeo.repositories.classificators.ConsistencyRepository;
import org.geoproject.ingeo.repositories.classificators.GenesisRepository;
import org.geoproject.ingeo.repositories.classificators.HatchingRepository;
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

    private final GenesisRepository genesisRepository;
    private final HatchingRepository hatchingRepository;
    private final ConsistencyRepository consistencyRepository;

    @Named("getSoilSubkindById")
    public SoilSubkind getSoilSubkindById(Long id) {
        var soilSubkind = soilSubkindRepository.findById(id);

        return soilSubkind.orElse(null);
    }

    @Named("getSoilSubkindAdjById")
    public SoilSubkindAdj getSoilSubkindAdjById(Long id) {
        var soilSubkindAdj = soilSubkindAdjRepository.findById(id);

        return soilSubkindAdj.orElse(null);
    }

    @Named("getGenesisDtoById")
    public GenesisDto getGenesisDtoById(Long id) {
        var genesis = genesisRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("Genesis")));

        var genesisDto = new GenesisDto();
        genesisDto.setId(genesis.getId());
        genesisDto.setGenesisCode(genesis.getCodeUni());
        genesisDto.setGenesisDescription(genesis.getName());

        return genesisDto;
    }

    @Named("getHatchingDtoById")
    public HatchingDto getHatchingDtoById(Long id) {
        var hatching = hatchingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("Hatching")));

        var hatchingDto = new HatchingDto();
        hatchingDto.setId(hatching.getId());
        hatchingDto.setShortName(hatching.getShortName());

        return hatchingDto;
    }

    @Named("getConsistencyDtoById")
    public ConsistencyDto getConsistencyDtoById(Long id) {
        var consistency = consistencyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("Consistency")));

        var consistencyDto = new ConsistencyDto();
        consistencyDto.setId(consistency.getId());
        consistencyDto.setConsistencyType(consistency.getConsistencyType());

        return consistencyDto;
    }

    @Named("getGenesisById")
    public Genesis getGenesisById(Long id) {
        System.out.println("CHECK IDDDD");
        System.out.println(id);
        return genesisRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("Genesis")));
    }

    @Named("getHatchingById")
    public Hatching getHatchingById(Long id) {
        return hatchingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("Hatching")));
    }

    @Named("getConsistencyById")
    public Consistency getConsistencyById(Long id) {
        return consistencyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("Consistency")));
    }
}
