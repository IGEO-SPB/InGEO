package org.geoproject.ingeo.services.classificators.impl;

import org.geoproject.ingeo.dto.classificators.RingDto;
import org.geoproject.ingeo.exceptions.NoContainerNumberEnteredException;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.mapper.classificators.RingMapper;
import org.geoproject.ingeo.models.classificators.Ring;
import org.geoproject.ingeo.repositories.classificators.RingRepository;
import org.geoproject.ingeo.services.classificators.RingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_NOT_ACTIVE;

@Service
@RequiredArgsConstructor
public class RingServiceImpl implements RingService {

    private final RingRepository ringRepository;
    private final RingMapper ringMapper;

    @Override
    public Ring getByNumber(String number) {
        if (Objects.nonNull(number) && !number.isEmpty()) {
            return ringRepository.findByNumber(number)
                    .orElseThrow(() -> new NotFoundException("Такого кольца нет в базе данных."));
        }
        throw new NoContainerNumberEnteredException("Номер кольца не заполнен");
    }

    @Override
    public List<Ring> findAll() {
        return ringRepository.findAll();
    }

    @Transactional
    @Override
    public void save(Ring ring) {
        ringRepository.save(ring);
    }

    @Transactional
    @Override
    public void saveAll(List<Ring> rings) {
        ringRepository.saveAll(rings);
    }

    @Override
    public List<RingDto> getDTOs(List<Ring> activeEntities) {
        return ringMapper.ringToRingDto(activeEntities);
    }

    @Override
    public void updateFromDtos(List<Ring> entities, List<RingDto> dtos) {
        ringMapper.updateRing(entities, dtos);

        ringRepository.saveAll(entities);
    }

    @Override
    @Transactional
    public void create(List<RingDto> dtos) {
        List<RingDto> collectedDtos = dtos.stream()
                .filter(dto -> !dto.getNumber().isEmpty())
                .toList();

        var newRings = ringMapper.ringDtoToRing(collectedDtos);

        var allRings = ringRepository.findAll();

        for (var oldRing : allRings) {
            if (newRings.contains(oldRing)) {
                ringRepository.delete(oldRing);
            }
        }

        ringRepository.saveAll(newRings);
    }

    @Override
    @Transactional
    public void delete(RingDto ringDto, List<Ring> rings) {
        Ring deletedRing = getByNumber(ringDto.getNumber());

        deletedRing.setIsActive(ENTITY_IS_NOT_ACTIVE);

        ringRepository.save(deletedRing);
    }

    @Override
    public List<Ring> getActiveEntities(List<Ring> entities) {
        List<Ring> activeRings = entities.stream()
                .filter(Ring::getIsActive)
                .toList();

//        if (activeRings.isEmpty()) {
//            throw new NoActiveEntitiesException("Нет активных колец");
//        }

        return activeRings;
    }
}
