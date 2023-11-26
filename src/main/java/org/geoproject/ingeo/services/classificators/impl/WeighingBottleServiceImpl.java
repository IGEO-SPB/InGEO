package com.geoproject.igeo.services.classificators.impl;

import com.geoproject.igeo.dto.classificators.WeighingBottleDto;
import com.geoproject.igeo.exceptions.ConflictException;
import com.geoproject.igeo.exceptions.NoActiveEntitiesException;
import com.geoproject.igeo.exceptions.NotFoundException;
import com.geoproject.igeo.mapper.classificators.WeighingBottleMapper;
import com.geoproject.igeo.models.classificators.WeighingBottle;
import com.geoproject.igeo.repositories.classificators.WeighingBottleRepository;
import com.geoproject.igeo.services.classificators.WeighingBottleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.geoproject.igeo.constants.ServiceConstants.ENTITY_IS_NOT_ACTIVE;

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
