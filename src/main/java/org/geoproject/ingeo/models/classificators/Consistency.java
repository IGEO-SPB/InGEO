package org.geoproject.ingeo.models.classificators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.geoproject.ingeo.models.Ege;

import java.util.List;

/**
 * Таблица с типами консистенции
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "classif_consistency")
public class Consistency {

    /**
     * ВА: N_tip, № типа по CREDO
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ВА: T_CNS, тип опробования
     */
    @Column(name = "consistency_type")
    private String consistencyType;

    /**
     * ВА: R_CNS, № типа по ROBUR
     */
    @Column(name = "robur_number")
    private String roburNumber;

    @OneToMany
    @JoinColumn(name = "consistency_id")
    private List<Ege> egeList;
}
