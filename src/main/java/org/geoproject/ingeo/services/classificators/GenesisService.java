package com.geoproject.igeo.services.classificators;

import com.geoproject.igeo.models.Genesis;
import com.geoproject.igeo.repositories.GenesisRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public interface GenesisService {

    List<Genesis> findAll();

    ObservableList<Genesis> getAllGenesisTypes();

    Genesis findOne(int id);

    Genesis findByCode(String code);

    Genesis findByGiId(int giId);
    }
