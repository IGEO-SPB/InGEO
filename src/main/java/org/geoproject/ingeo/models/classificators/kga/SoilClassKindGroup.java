package org.geoproject.ingeo.models.classificators.kga;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "classif_soil_class_kind_group")
/**
 * Таблица для уникальных sk_group для вывода в чойсбоксе soilKindChoiceBox
 * Этой таблицы нет в схеме ВА для КГА - добавлена нами для нормализации БД
 */
public class SoilClassKindGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "soil_class_id")
    private SoilClass soilClass;

    @Column(name = "soil_kind_group")
    private String soilKindGroup;

    @OneToMany
    @JoinColumn(name = "soil_class_kind_group_id")
    private List<SoilKind> soilKindList;
}
