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
import org.geoproject.ingeo.models.Ege;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "CL_COLOR")
public class Color {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Назначение поля неизвестно - у ВА в таблице везде '4'
     */
    @Column(name = "CL_ID")
    private Long clId;

    @Column(name = "CLT_NAME")
    private String cltName;

    /**
     * Описание, аналогичное cltName, помещенное в тэг <name></name>
     */
    @Column(name = "CLT_DATA")
    private String cltData;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATOR")
    private String creator;

    @Column(name = "DATE_CR")
    private String dateCr;

    @Column(name = "ARCHIVER")
    private String archiver;

    @Column(name = "DATE_ARCH")
    private String dateArch;

    @OneToMany
    @JoinColumn(name = "color")
    private List<Ege> egeList;
}
