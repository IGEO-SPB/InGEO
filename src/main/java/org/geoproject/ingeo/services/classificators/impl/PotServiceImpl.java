package org.geoproject.ingeo.services.classificators.impl;

import org.geoproject.ingeo.dto.classificators.PotDto;
import org.geoproject.ingeo.exceptions.ConflictException;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.mapper.classificators.PotMapper;
import org.geoproject.ingeo.models.classificators.Pot;
import org.geoproject.ingeo.repositories.classificators.PotRepository;
import org.geoproject.ingeo.services.classificators.PotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_NOT_ACTIVE;

@Service
@RequiredArgsConstructor
public class PotServiceImpl implements PotService {

    private final PotRepository potRepository;
    private final PotMapper potMapper;

    @Override
    public Pot getByNumber(String number) {
        if (Objects.nonNull(number) && !number.isEmpty()) {
            return potRepository.findByNumber(number)
                    .orElseThrow(() -> new NotFoundException("Такого тигля нет в базе данных."));
        }
        throw new ConflictException("Номер тигля не заполнен");
    }

    @Override
    public List<Pot> findAll() {
        return potRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    @Transactional
    public void save(Pot pot) {
        potRepository.save(pot);
    }

    @Override
    public void saveAll(List<Pot> pots) {
        potRepository.saveAll(pots);
    }

    @Override
    @Transactional
    public void delete(PotDto potDto, List<Pot> pots) {
        Pot deletedPot = getByNumber(potDto.getNumber());

        deletedPot.setIsActive(ENTITY_IS_NOT_ACTIVE);

        potRepository.save(deletedPot);
    }

    @Override
    public List<PotDto> getDTOs(List<Pot> pots) {
        return potMapper.potToPotDto(pots);
    }

    @Override
    @Transactional
    public void updateFromDtos(List<Pot> pots, List<PotDto> dtos) {
        potMapper.updatePot(pots, dtos);

        potRepository.saveAll(pots);
    }

    @Override
    @Transactional
    public void create(List<PotDto> dtos) {
        List<PotDto> collectedDtos = dtos.stream()
                .filter(dto -> !dto.getNumber().isEmpty())
                .toList();

        var newPots = potMapper.potDtoToPot(collectedDtos);

        var allPots = potRepository.findAll();

        for (var oldPot : allPots) {
            if (newPots.contains(oldPot)) {
                potRepository.delete(oldPot);
            }
        }

        potRepository.saveAll(newPots);
    }

    @Override
    public List<Pot> getActiveEntities(List<Pot> entities) {
        List<Pot> activePots = entities.stream()
                .filter(Pot::getIsActive)
                .toList();

//        if (activePots.isEmpty()) {
//            throw new NoActiveEntitiesException("Нет активных тиглей");
//        }

        return activePots;
    }
}
