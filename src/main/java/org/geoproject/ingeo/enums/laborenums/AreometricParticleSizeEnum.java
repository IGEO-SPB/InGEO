package org.geoproject.ingeo.enums.laborenums;

import lombok.Getter;

@Getter
public enum AreometricParticleSizeEnum {
    PARTICLE_SIZE_20(1, 20.0F),
    PARTICLE_SIZE_10(2, 10.0F),
    PARTICLE_SIZE_5(3, 5.0F), //для 10-5-2
    PARTICLE_SIZE_2(4, 2.0F),
    PARTICLE_SIZE_1(5, 1.0F),
    PARTICLE_SIZE_05(6, 0.5F),
    PARTICLE_SIZE_025(7, 0.25F),
    PARTICLE_SIZE_01(8, 0.1F),
    PARTICLE_SIZE_005(9, 0.05F),
    PARTICLE_SIZE_001(10, 0.01F),
    PARTICLE_SIZE_0005(11, 0.005F),
    PARTICLE_SIZE_0002(12, 0.002F),
    PARTICLE_SIZE_0001(13, 0.001F);


    AreometricParticleSizeEnum(int id, float size) {
        this.id = id;
        this.size = size;
    }

    private int id;
    private float size;

    public static AreometricParticleSizeEnum getById(int id) {
        for (AreometricParticleSizeEnum e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }

        throw new IllegalArgumentException("No enum constant with id " + id);
    }

}