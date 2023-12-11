package org.geoproject.ingeo.repositories.classificators;

import org.geoproject.ingeo.models.classificators.Consistency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsistencyRepository extends JpaRepository<Consistency, Long> {

}
