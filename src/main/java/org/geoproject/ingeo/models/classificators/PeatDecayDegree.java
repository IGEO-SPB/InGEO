package com.geoproject.igeo.models.classificators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "classif_peat_decay_degree")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class PeatDecayDegree {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "P250")
    private int p250;

    @Column(name = "value")
    private float value;


}
