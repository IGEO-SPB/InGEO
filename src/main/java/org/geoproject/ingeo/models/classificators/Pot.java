package com.geoproject.igeo.models.classificators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "classif_pot")
public class Pot {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pot pot = (Pot) o;
        return Objects.equals(number, pot.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
