package org.geoproject.ingeo.repositories.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {


}
