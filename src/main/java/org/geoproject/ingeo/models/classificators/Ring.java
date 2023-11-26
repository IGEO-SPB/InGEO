package org.geoproject.ingeo.models.classificators;

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
@RequiredArgsConstructor
@Entity
@Table(name = "classif_ring")
public class Ring {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "d1")
    private Integer d1;

    @Column(name = "d2")
    private Integer d2;

    @Column(name = "h")
    private Float height;

    @Column(name = "volume")
    private Float volume;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ring ring = (Ring) o;
        return Objects.equals(number, ring.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
