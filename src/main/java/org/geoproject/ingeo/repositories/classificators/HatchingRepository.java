package org.geoproject.ingeo.repositories.classificators;

import org.geoproject.ingeo.models.classificators.Hatching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HatchingRepository extends JpaRepository<Hatching, Long> {

}
