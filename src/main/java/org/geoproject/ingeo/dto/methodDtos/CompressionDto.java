package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.models.Sample;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CompressionDto {
    private Long id;
    private Sample sample;
    private StringProperty labNumber = new SimpleStringProperty();
    private Property<Integer> ringNumber = new SimpleObjectProperty<>(null);
    private Property<Double> ringHeight = new SimpleObjectProperty<>(null);
    private Property<Double> porosityCoefficient = new SimpleObjectProperty<>(null);

    public Integer getRingNumber() {
        return ringNumber.getValue();
    }

    public Property<Integer> ringNumberProperty() {
        return ringNumber;
    }

    public void setRingNumber(Integer ringNumber) {
        this.ringNumber.setValue(ringNumber);
    }

    public Double getRingHeight() {
        return ringHeight.getValue();
    }

    public Property<Double> ringHeightProperty() {
        return ringHeight;
    }

    public void setRingHeight(Double ringHeight) {
        this.ringHeight.setValue(ringHeight);
    }

    public Double getPorosityCoefficient() {
        return porosityCoefficient.getValue();
    }

    public Property<Double> porosityCoefficientProperty() {
        return porosityCoefficient;
    }

    public void setPorosityCoefficient(Double porosityCoefficient) {
        this.porosityCoefficient.setValue(porosityCoefficient);
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

    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompressionItemDto {
        private Property<Double> pressure = new SimpleObjectProperty<>(null);
        private Property<Double> discharge = new SimpleObjectProperty<>(null);
        private Property<Double> deformation = new SimpleObjectProperty<>(null);
        private Property<Double> porosity = new SimpleObjectProperty<>(null);

        public Double getPressure() {
            return pressure.getValue();
        }

        public Property<Double> pressureProperty() {
            return pressure;
        }

        public void setPressure(Double pressure) {
            this.pressure.setValue(pressure);
        }

        public Double getDischarge() {
            return discharge.getValue();
        }

        public Property<Double> dischargeProperty() {
            return discharge;
        }

        public void setDischarge(Double discharge) {
            this.discharge.setValue(discharge);
        }

        public Double getDeformation() {
            return deformation.getValue();
        }

        public Property<Double> deformationProperty() {
            return deformation;
        }

        public void setDeformation(Double deformation) {
            this.deformation.setValue(deformation);
        }

        public Double getPorosity() {
            return porosity.getValue();
        }

        public Property<Double> porosityProperty() {
            return porosity;
        }

        public void setPorosity(Double porosity) {
            this.porosity.setValue(porosity);
        }
    }

    private CompressionItemDto item_00000 = new CompressionItemDto();
    private CompressionItemDto item_00125 = new CompressionItemDto();
    private CompressionItemDto item_00250 = new CompressionItemDto();
    private CompressionItemDto item_00500 = new CompressionItemDto();
    private CompressionItemDto item_00750 = new CompressionItemDto();
    private CompressionItemDto item_01000 = new CompressionItemDto();
    private CompressionItemDto item_02000 = new CompressionItemDto();
    private CompressionItemDto item_03000 = new CompressionItemDto();
    private CompressionItemDto item_04000 = new CompressionItemDto();
    private CompressionItemDto item_05000 = new CompressionItemDto();
    private CompressionItemDto item_06000 = new CompressionItemDto();
    private CompressionItemDto item_07000 = new CompressionItemDto();
    private CompressionItemDto item_08000 = new CompressionItemDto();
    private CompressionItemDto item_09000 = new CompressionItemDto();
    private CompressionItemDto item_10000 = new CompressionItemDto();
}
