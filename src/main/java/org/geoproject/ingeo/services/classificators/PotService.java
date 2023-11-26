package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.dto.classificators.PotDto;
import org.geoproject.ingeo.models.classificators.Pot;

import java.util.List;

public interface PotService extends ClassificatorService<Pot, PotDto> {

    Pot getByNumber(String number);

    List<Pot> findAll();

    void save(Pot pot);

    void saveAll(List<Pot> pots);

    void delete(PotDto potDto, List<Pot> pots);

    @Override
    List<PotDto> getDTOs(List<Pot> activePots);

    @Override
    void updateFromDtos(List<Pot> pots, List<PotDto> dtos);

    @Override
    void create(List<PotDto> dtos);

    @Override
    List<Pot> getActiveEntities(List<Pot> entities);
}
