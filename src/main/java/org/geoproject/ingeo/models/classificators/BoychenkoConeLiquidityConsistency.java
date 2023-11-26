package org.geoproject.ingeo.models.classificators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "classif_boychenko_cone_liquidity_consistency")
public class BoychenkoConeLiquidityConsistency {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "boychenko_cone_immersion_depth")
    private float boychenkoConeImmersionDepth;

    @Column(name = "cb_consistency")
    private float cbConsistency;

}
