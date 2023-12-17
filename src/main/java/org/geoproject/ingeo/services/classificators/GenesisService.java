package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.models.classificators.Genesis;
import javafx.collections.ObservableList;

import java.util.List;

public interface GenesisService {

    List<Genesis> getAll();

    ObservableList<Genesis> getAllGenesisTypes();

    Genesis findOne(Long id);

    Genesis findByCode(String code);

    Genesis findByGiId(int giId);

    List<GenesisDto> getEgeDtos();
}
