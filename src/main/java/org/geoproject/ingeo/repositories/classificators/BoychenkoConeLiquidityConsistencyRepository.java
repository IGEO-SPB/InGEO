package org.geoproject.ingeo.repositories.classificators;

import org.geoproject.ingeo.models.classificators.BoychenkoConeLiquidityConsistency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoychenkoConeLiquidityConsistencyRepository extends JpaRepository<BoychenkoConeLiquidityConsistency, Integer> {
    BoychenkoConeLiquidityConsistency findByBoychenkoConeImmersionDepth(float boychenkoConeImmersionDepth);
}
