package com.geoproject.igeo.services.classificators.impl;

import com.geoproject.igeo.models.Genesis;
import com.geoproject.igeo.repositories.GenesisRepository;
import com.geoproject.igeo.services.classificators.GenesisService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenesisServiceImpl implements GenesisService {

    private final GenesisRepository genesisRepository;

    public GenesisServiceImpl(GenesisRepository genesisRepository) {
        this.genesisRepository = genesisRepository;
    }

    public List<Genesis> findAll() {
        return genesisRepository.findAll();
    }

    public ObservableList<Genesis> getAllGenesisTypes() {
        ObservableList<Genesis> egeObservableList = FXCollections.observableArrayList();
        egeObservableList.addAll(findAll());
        return egeObservableList;
    }

    public Genesis findOne(int id) {
        Optional<Genesis> foundGenesis = genesisRepository.findById(id);
        return foundGenesis.orElse(null);
    }

    public Genesis findByCode(String code) {
        return genesisRepository.findByCodeUni(code);
    }

    public Genesis findByGiId(int giId) {
        return genesisRepository.findByGiId(giId);
    }

}
