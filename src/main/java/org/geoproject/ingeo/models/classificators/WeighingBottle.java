package com.geoproject.igeo.models.classificators;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "classif_weighing_bottle")
@Getter
@Setter
@RequiredArgsConstructor
public class WeighingBottle {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "weight")
    private float weight;

    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeighingBottle that = (WeighingBottle) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
