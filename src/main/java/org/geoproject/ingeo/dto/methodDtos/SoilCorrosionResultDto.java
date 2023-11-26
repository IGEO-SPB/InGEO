package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.models.Sample;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SoilCorrosionResultDto {
    private Long id;
    private Sample sample;
    private StringProperty labNumber = new SimpleStringProperty();
    private FloatProperty uesg = new SimpleFloatProperty();
    private StringProperty corrAggrDegreeUesg = new SimpleStringProperty();
    private FloatProperty pkt = new SimpleFloatProperty();
    private StringProperty corrAggrDegreePkt = new SimpleStringProperty();

    public float getUesg() {
        return uesg.get();
    }

    public FloatProperty uesgProperty() {
        return uesg;
    }

    public void setUesg(float uesg) {
        this.uesg.set(uesg);
    }

    public String getCorrAggrDegreeUesg() {
        return corrAggrDegreeUesg.get();
    }

    public StringProperty corrAggrDegreeUesgProperty() {
        return corrAggrDegreeUesg;
    }

    public void setCorrAggrDegreeUesg(String corrAggrDegreeUesg) {
        this.corrAggrDegreeUesg.set(corrAggrDegreeUesg);
    }

    public float getPkt() {
        return pkt.get();
    }

    public FloatProperty pktProperty() {
        return pkt;
    }

    public void setPkt(float pkt) {
        this.pkt.set(pkt);
    }

    public String getCorrAggrDegreePkt() {
        return corrAggrDegreePkt.get();
    }

    public StringProperty corrAggrDegreePktProperty() {
        return corrAggrDegreePkt;
    }

    public void setCorrAggrDegreePkt(String corrAggrDegreePkt) {
        this.corrAggrDegreePkt.set(corrAggrDegreePkt);
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
