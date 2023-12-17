package org.geoproject.ingeo.services.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.Color;

import java.util.List;

public interface ColorService {

    List<Color> getAll(String sortField);
}
