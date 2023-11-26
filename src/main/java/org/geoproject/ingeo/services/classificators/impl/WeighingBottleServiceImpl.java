package org.geoproject.ingeo.services.classificators.impl;

import org.geoproject.ingeo.dto.classificators.WeighingBottleDto;
import org.geoproject.ingeo.exceptions.ConflictException;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.mapper.classificators.WeighingBottleMapper;
import org.geoproject.ingeo.models.classificators.WeighingBottle;
import org.geoproject.ingeo.repositories.classificators.WeighingBottleRepository;
import org.geoproject.ingeo.services.classificators.WeighingBottleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_NOT_ACTIVE;

@Service
@RequiredArgsConstructor
public class WeighingBottleServiceImpl implements WeighingBottleService {

    private final WeighingBottleRepository weighingBottleRepository;
    private final WeighingBottleMapper weighingBottleMapper;

    @Override
    public WeighingBottle getByNumber(String number) {
        if (Objects.nonNull(number) && !number.isEmpty()) {
            return weighingBottleRepository.findByNumber(number)
                    .orElseThrow(() -> new NotFoundException("Такого бюкса нет в базе данных."));
        }
        throw new ConflictException("Номер бюкса не заполнен");
    }

    @Override
    public void create(List<WeighingBottleDto> dtos) {
        List<WeighingBottleDto> collectedDtos = dtos.stream()
                .filter(dto -> !dto.getNumber().isEmpty())
                .toList();

        var newWeighingBottles = weighingBottleMapper.weighingBottleDtoToWeighingBottle(collectedDtos);

        var allWeighingBottles = weighingBottleRepository.findAll();

        for (var oldallWeighingBottle : allWeighingBottles) {
            if (newWeighingBottles.contains(oldallWeighingBottle)) {
                weighingBottleRepository.delete(oldallWeighingBottle);
            }
        }

        weighingBottleRepository.saveAll(newWeighingBottles);
    }

    @Override
    @Transactional
    public void delete(WeighingBottleDto dto, List<WeighingBottle> entities) {
        WeighingBottle deletedWeighingBottle = getByNumber(dto.getNumber());

        deletedWeighingBottle.setIsActive(ENTITY_IS_NOT_ACTIVE);

        weighingBottleRepository.save(deletedWeighingBottle);
    }

    @Override
    public List<WeighingBottle> getAll() {
        return weighingBottleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<WeighingBottleDto> getDTOs(List<WeighingBottle> activeEntities) {
        return weighingBottleMapper.weighingBottleToWeighingBottleDto(activeEntities);
    }

    @Override
    public void updateFromDtos(List<WeighingBottle> entities, List<WeighingBottleDto> dtos) {
        weighingBottleMapper.updateWeighingBottle(entities, dtos);

        weighingBottleRepository.saveAll(entities);
    }

    @Override
    public List<WeighingBottle> getActiveEntities(List<WeighingBottle> entities) {
        List<WeighingBottle> activeWeighingBottles = entities.stream()
                .filter(WeighingBottle::getIsActive)
                .toList();

//        if (activeWeighingBottles.isEmpty()) {
//            throw new NoActiveEntitiesException("Нет активных бюксов");
//        }

        return activeWeighingBottles;
    }
}
