package org.geoproject.ingeo.models.classificators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.geoproject.ingeo.models.Ege;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "classif_genesis")
public class Genesis {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gi_id")
    private int giId;

    @Column(name = "gi_code")
    private String code;

    @Column(name = "gi_code_uni")
    private String codeUni;

    //в представлении ВА gi_name - описание генезиса
    @Column(name = "gi_name")
    private String name;

    @Column(name = "gi_color_r")
    private Integer colorR;

    @Column(name = "gi_color_g")
    private Integer colorG;

    @Column(name = "gi_color_b")
    private Integer colorB;

    @Column(name = "gi_note")
    private String note;

    @Column(name = "gi_color")
    private String color;

    @Column(name = "gi_order")
    private String order;

    @OneToMany(mappedBy = "genesis", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Ege> egeList;
}
