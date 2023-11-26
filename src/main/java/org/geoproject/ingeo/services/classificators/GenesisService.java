package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.models.classificators.Genesis;
import javafx.collections.ObservableList;

import java.util.List;

public interface GenesisService {

    List<Genesis> findAll();

    ObservableList<Genesis> getAllGenesisTypes();

    Genesis findOne(int id);

    Genesis findByCode(String code);

    Genesis findByGiId(int giId);
    }
