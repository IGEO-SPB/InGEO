package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.models.Sample;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SoilCorrosionInputDto {
    private Long id;
    private Sample sample;
    private StringProperty labNumber = new SimpleStringProperty();
    private FloatProperty current = new SimpleFloatProperty();
    private FloatProperty voltage = new SimpleFloatProperty();
    private FloatProperty resistance = new SimpleFloatProperty();
    private FloatProperty cathodeCurrent = new SimpleFloatProperty();

    public float getCurrent() {
        return current.get();
    }

    public FloatProperty currentProperty() {
        return current;
    }

    public void setCurrent(float current) {
        this.current.set(current);
    }

    public float getVoltage() {
        return voltage.get();
    }

    public FloatProperty voltageProperty() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage.set(voltage);
    }

    public float getResistance() {
        return resistance.get();
    }

    public FloatProperty resistanceProperty() {
        return resistance;
    }

    public void setResistance(float resistance) {
        this.resistance.set(resistance);
    }

    public float getCathodeCurrent() {
        return cathodeCurrent.get();
    }

    public FloatProperty cathodeCurrentProperty() {
        return cathodeCurrent;
    }

    public void setCathodeCurrent(float cathodeCurrent) {
        this.cathodeCurrent.set(cathodeCurrent);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabNumber() {
        return labNumber.get();
    }

    public StringProperty labNumberProperty() {
        return labNumber;
    }

    public void setLabNumber(String labNumber) {
        this.labNumber.set(labNumber);
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }
}
