package org.geoproject.ingeo.dto.mainViewsDto;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditProjectDto {

    private Long id;

    private StringProperty contractNumber = new SimpleStringProperty();
    private StringProperty projectName = new SimpleStringProperty();
    private StringProperty region = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty district = new SimpleStringProperty();
    private StringProperty town = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty constructionType = new SimpleStringProperty();
    private StringProperty reportName = new SimpleStringProperty();
    private StringProperty archiveNumber = new SimpleStringProperty();
    private FloatProperty absoluteMediumWinterTemperature = new SimpleFloatProperty();
    private ObjectProperty<LocalDate> assignmentDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();
    private StringProperty projectingStage  = new SimpleStringProperty();
    private FloatProperty coordinateX = new SimpleFloatProperty();
    private FloatProperty coordinateY = new SimpleFloatProperty();
    private StringProperty createdBy = new SimpleStringProperty();
    private StringProperty executor = new SimpleStringProperty();
    private StringProperty approver = new SimpleStringProperty();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber.get();
    }

    public StringProperty contractNumberProperty() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber.set(contractNumber);
    }

    public String getProjectName() {
        return projectName.get();
    }

    public StringProperty projectNameProperty() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }

    public String getRegion() {
        return region.get();
    }

    public StringProperty regionProperty() {
        return region;
    }

    public void setRegion(String region) {
        this.region.set(region);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getDistrict() {
        return district.get();
    }

    public StringProperty districtProperty() {
        return district;
    }

    public void setDistrict(String district) {
        this.district.set(district);
    }

    public String getTown() {
        return town.get();
    }

    public StringProperty townProperty() {
        return town;
    }

    public void setTown(String town) {
        this.town.set(town);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getConstructionType() {
        return constructionType.get();
    }

    public StringProperty constructionTypeProperty() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType.set(constructionType);
    }

    public String getReportName() {
        return reportName.get();
    }

    public StringProperty reportNameProperty() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName.set(reportName);
    }

    public String getArchiveNumber() {
        return archiveNumber.get();
    }

    public StringProperty archiveNumberProperty() {
        return archiveNumber;
    }

    public void setArchiveNumber(String archiveNumber) {
        this.archiveNumber.set(archiveNumber);
    }

    public float getAbsoluteMediumWinterTemperature() {
        return absoluteMediumWinterTemperature.get();
    }

    public FloatProperty absoluteMediumWinterTemperatureProperty() {
        return absoluteMediumWinterTemperature;
    }

    public void setAbsoluteMediumWinterTemperature(float absoluteMediumWinterTemperature) {
        this.absoluteMediumWinterTemperature.set(absoluteMediumWinterTemperature);
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate.get();
    }

    public ObjectProperty<LocalDate> assignmentDateProperty() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate.set(assignmentDate);
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public LocalDate getEndDate() {
        return endDate.get();
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate.set(endDate);
    }

    public String getProjectingStage() {
        return projectingStage.get();
    }

    public StringProperty projectingStageProperty() {
        return projectingStage;
    }

    public void setProjectingStage(String projectingStage) {
        this.projectingStage.set(projectingStage);
    }

    public float getCoordinateX() {
        return coordinateX.get();
    }

    public FloatProperty coordinateXProperty() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX.set(coordinateX);
    }

    public float getCoordinateY() {
        return coordinateY.get();
    }

    public FloatProperty coordinateYProperty() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY.set(coordinateY);
    }

    public String getCreatedBy() {
        return createdBy.get();
    }

    public StringProperty createdByProperty() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }

    public String getExecutor() {
        return executor.get();
    }

    public StringProperty executorProperty() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor.set(executor);
    }

    public String getApprover() {
        return approver.get();
    }

    public StringProperty approverProperty() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver.set(approver);
    }
}