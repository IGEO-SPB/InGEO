package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.models.classificators.BoychenkoConeLiquidityConsistency;

public interface BoychenkoConeLiquidityConsistencyService {

    BoychenkoConeLiquidityConsistency findByBoychenkoConeImmersionDepth(float boychenkoConeImmersionDepth);

    String defineCbLiquidityIndexName(float boychenkoConeImmersionDepth);

    }
