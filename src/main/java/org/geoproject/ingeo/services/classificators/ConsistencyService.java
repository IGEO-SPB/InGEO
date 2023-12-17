package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.models.classificators.Consistency;

import java.util.List;

public interface ConsistencyService {

    List<Consistency> getAll();

    List<ConsistencyDto> getConsistencyDtos();
}
