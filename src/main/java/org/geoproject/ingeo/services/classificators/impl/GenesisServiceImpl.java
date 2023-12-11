package org.geoproject.ingeo.services.classificators.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.mapper.GenesisMapper;
import org.geoproject.ingeo.models.classificators.Genesis;
import org.geoproject.ingeo.repositories.classificators.GenesisRepository;
import org.geoproject.ingeo.services.classificators.GenesisService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenesisServiceImpl implements GenesisService {

    private final GenesisRepository genesisRepository;
    private final GenesisMapper genesisMapper;

    public List<Genesis> getAll() {
        return genesisRepository.findAll();
    }

    public ObservableList<Genesis> getAllGenesisTypes() {
        ObservableList<Genesis> egeObservableList = FXCollections.observableArrayList();
        egeObservableList.addAll(getAll());
        return egeObservableList;
    }

    public Genesis findOne(Long id) {
        Optional<Genesis> foundGenesis = genesisRepository.findById(id);
        return foundGenesis.orElse(null);
    }

    public Genesis findByCode(String code) {
        return genesisRepository.findByCodeUni(code);
    }

    public Genesis findByGiId(int giId) {
        return genesisRepository.findByGiId(giId);
    }

    @Override
    public List<GenesisDto> getEgeDtos() {
        var genesisList = getAll();

        return genesisMapper.genesisToGenesisDto(genesisList);
    }
}
