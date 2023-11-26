package org.geoproject.ingeo.controllers.laborMethods.compression;

import org.geoproject.ingeo.dto.CompressionDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.classificators.Ring;
import org.geoproject.ingeo.services.classificators.RingService;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.methodViews.CompressionService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
@CommonsLog
public class CompressionInputController implements Initializable {

  @FXML
  public LineChart<Double, Double> chart;

  private CurrentState state;
  private SampleService sampleService;
  private RingService ringService;
  private CompressionService service;
  private ApplicationContext applicationContext;

  public CompressionInputController(CurrentState state, SampleService sampleService, RingService ringService,
      CompressionService service, ApplicationContext applicationContext) {
    this.state = state;
    this.sampleService = sampleService;
    this.ringService = ringService;
    this.service = service;
    this.applicationContext = applicationContext;
  }

  @FXML
  public ChoiceBox<String> sampleChoiceBox;

  @FXML
  public TextField offloadInput00000;
  @FXML
  public TextField offloadInput00125;
  @FXML
  public TextField offloadInput00250;
  @FXML
  public TextField offloadInput00500;
  @FXML
  public TextField offloadInput00750;
  @FXML
  public TextField offloadInput01000;
  @FXML
  public TextField offloadInput02000;
  @FXML
  public TextField offloadInput03000;
  @FXML
  public TextField offloadInput04000;
  @FXML
  public TextField offloadInput05000;
  @FXML
  public TextField offloadInput06000;
  @FXML
  public TextField offloadInput07000;
  @FXML
  public TextField offloadInput08000;
  @FXML
  public TextField offloadInput09000;
  @FXML
  public TextField offloadInput10000;
  @FXML
  public TextField pressureInput00000;
  @FXML
  public TextField pressureInput00125;
  @FXML
  public TextField pressureInput00250;
  @FXML
  public TextField pressureInput00500;
  @FXML
  public TextField pressureInput00750;
  @FXML
  public TextField pressureInput01000;
  @FXML
  public TextField pressureInput02000;
  @FXML
  public TextField pressureInput03000;
  @FXML
  public TextField pressureInput04000;
  @FXML
  public TextField pressureInput05000;
  @FXML
  public TextField pressureInput06000;
  @FXML
  public TextField pressureInput07000;
  @FXML
  public TextField pressureInput08000;
  @FXML
  public TextField pressureInput09000;
  @FXML
  public TextField pressureInput10000;

  @FXML
  public TextField porosityCoef;
  @FXML
  public ChoiceBox<String> ringNumberChoiceBox;
  @FXML
  public TextField ringHeightTextField;
  private final StringConverter<Double> converter = new StringConverter<>() {
    @Override
    public String toString(Double number) {
      if (number == null)
        return "";
      else
        return number.toString();
    }

    @Override
    public Double fromString(String s) {
      s = s.trim();
      if (s.isEmpty())
        return null;

      Number num = (Math.round(Double.parseDouble(s) * 100));
      return num.doubleValue() / 100;
    }
  };

  private void bindInput(TextField field, Property<Double> property) {
    Bindings.bindBidirectional(field.textProperty(), property, converter);
    field.focusedProperty().addListener((observableValue, aBoolean, hasFocus) -> {
      if (!hasFocus) {
        field.fireEvent(new ActionEvent());
      }
    });
    field.textProperty().addListener((observableValue, s, t1) -> {
      if (!Objects.equals(s, t1)) {
        redrawChart();
      }
    });

    field.setTextFormatter(new TextFormatter<>(converter, property.getValue(), change -> {
      try {
        var text = change.getControlNewText().trim();
        if (text.isEmpty())
          return change;

        var num = Double.parseDouble(text);
        return change;
      } catch (NumberFormatException ignored) {
      }
      return null;
    }));
  }

  private CompressionDto viewModel;

  private void initChart() {

  }

  private void redrawChart() {
    var res = new ArrayList<XYChart.Series<Double, Double>>();
    var data = pressureData();
    if(!data.getData().isEmpty()) res.add(data);
    data = offloadData();
    if(!data.getData().isEmpty()) res.add(data);

    chart.getData().setAll(FXCollections.observableList(res));
  }

  private void init() {
    log.info("init triggered");
    sampleList.addListener((ListChangeListener<Sample>) change -> {
      sampleChoiceBox
          .setItems(FXCollections.observableArrayList(sampleList.stream().map(Sample::getLaborNumber).toList()));
    });
    sampleList
        .setAll(sampleService.getBySurveyPoint(state.getSurveyPoint(), Sort.by(Sort.Direction.ASC, "laborNumber")));

    ringNumberChoiceBox
        .setItems(FXCollections.observableList(ringService.findAll().stream().map(Ring::getNumber).toList()));
    ringNumberChoiceBox.getSelectionModel().clearSelection();
    ringHeightTextField.setDisable(true);

//    final NumberAxis yAxis = new NumberAxis(-25, 0, 5);
    final ValueAxis<Double> yAxis = (ValueAxis<Double>) chart.getYAxis();
    yAxis.setTickLabelFormatter(new StringConverter<>() {
        @Override
        public String toString(Double aDouble) {
            return String.format("%3.1f", aDouble < 0 ? -aDouble : aDouble);
        }

        @Override
        public Double fromString(String s) {
            return Double.parseDouble(s);
        }
    });

    yAxis.setUpperBound(0D);
    yAxis.setLowerBound(-30D);
    yAxis.setLabel("Деформация, мм");

    final ValueAxis<Double> xAxis = (ValueAxis<Double>) chart.getXAxis();
    xAxis.setSide(Side.TOP);
    xAxis.setLabel("Давление, МПа");
    xAxis.setUpperBound(1D);
  }

  private void appendToSeries(XYChart.Series<Double, Double> series, Double x, Double y) {
    if (x == null || y == null)
      return;
    series.getData().add(new XYChart.Data<Double, Double>(x, -y));
  }

  private XYChart.Series<Double, Double> offloadData() {
    var series = new XYChart.Series<Double, Double>();

    series.setName("Деформация");
    appendToSeries(series, 0.0000D, viewModel.getItem_00000().getDischarge());
    appendToSeries(series, 0.0125D, viewModel.getItem_00125().getDischarge());
    appendToSeries(series, 0.0250D, viewModel.getItem_00250().getDischarge());
    appendToSeries(series, 0.0500D, viewModel.getItem_00500().getDischarge());
    appendToSeries(series, 0.0750D, viewModel.getItem_00750().getDischarge());
    appendToSeries(series, 0.1000D, viewModel.getItem_01000().getDischarge());
    appendToSeries(series, 0.2000D, viewModel.getItem_02000().getDischarge());
    appendToSeries(series, 0.3000D, viewModel.getItem_03000().getDischarge());
    appendToSeries(series, 0.4000D, viewModel.getItem_04000().getDischarge());
    appendToSeries(series, 0.5000D, viewModel.getItem_05000().getDischarge());
    appendToSeries(series, 0.6000D, viewModel.getItem_06000().getDischarge());
    appendToSeries(series, 0.7000D, viewModel.getItem_07000().getDischarge());
    appendToSeries(series, 0.8000D, viewModel.getItem_08000().getDischarge());
    appendToSeries(series, 0.9000D, viewModel.getItem_09000().getDischarge());
    appendToSeries(series, 1.0000D, viewModel.getItem_10000().getDischarge());

    return series;
  }

  private XYChart.Series<Double, Double> pressureData() {
    var series = new XYChart.Series<Double, Double>();

    series.setName("Давление");

    appendToSeries(series, 0.0000D, viewModel.getItem_00000().getPressure());
    appendToSeries(series, 0.0125D, viewModel.getItem_00125().getPressure());
    appendToSeries(series, 0.0250D, viewModel.getItem_00250().getPressure());
    appendToSeries(series, 0.0500D, viewModel.getItem_00500().getPressure());
    appendToSeries(series, 0.0750D, viewModel.getItem_00750().getPressure());
    appendToSeries(series, 0.1000D, viewModel.getItem_01000().getPressure());
    appendToSeries(series, 0.2000D, viewModel.getItem_02000().getPressure());
    appendToSeries(series, 0.3000D, viewModel.getItem_03000().getPressure());
    appendToSeries(series, 0.4000D, viewModel.getItem_04000().getPressure());
    appendToSeries(series, 0.5000D, viewModel.getItem_05000().getPressure());
    appendToSeries(series, 0.6000D, viewModel.getItem_06000().getPressure());
    appendToSeries(series, 0.7000D, viewModel.getItem_07000().getPressure());
    appendToSeries(series, 0.8000D, viewModel.getItem_08000().getPressure());
    appendToSeries(series, 0.9000D, viewModel.getItem_09000().getPressure());
    appendToSeries(series, 1.0000D, viewModel.getItem_10000().getPressure());

    return series;
  }

  private void loadDto(String labNumber, String from) {
    if (viewModel != null)
      log.info(viewModel.getRingNumber());
    viewModel = service.getCompressionForSample(labNumber);

    bindInput(pressureInput00000, viewModel.getItem_00000().pressureProperty());
    bindInput(pressureInput00125, viewModel.getItem_00125().pressureProperty());
    bindInput(pressureInput00250, viewModel.getItem_00250().pressureProperty());
    bindInput(pressureInput00500, viewModel.getItem_00500().pressureProperty());
    bindInput(pressureInput00750, viewModel.getItem_00750().pressureProperty());
    bindInput(pressureInput01000, viewModel.getItem_01000().pressureProperty());
    bindInput(pressureInput02000, viewModel.getItem_02000().pressureProperty());
    bindInput(pressureInput03000, viewModel.getItem_03000().pressureProperty());
    bindInput(pressureInput04000, viewModel.getItem_04000().pressureProperty());
    bindInput(pressureInput05000, viewModel.getItem_05000().pressureProperty());
    bindInput(pressureInput06000, viewModel.getItem_06000().pressureProperty());
    bindInput(pressureInput07000, viewModel.getItem_07000().pressureProperty());
    bindInput(pressureInput08000, viewModel.getItem_08000().pressureProperty());
    bindInput(pressureInput09000, viewModel.getItem_09000().pressureProperty());
    bindInput(pressureInput10000, viewModel.getItem_10000().pressureProperty());

    bindInput(offloadInput00000, viewModel.getItem_00000().dischargeProperty());
    bindInput(offloadInput00125, viewModel.getItem_00125().dischargeProperty());
    bindInput(offloadInput00250, viewModel.getItem_00250().dischargeProperty());
    bindInput(offloadInput00500, viewModel.getItem_00500().dischargeProperty());
    bindInput(offloadInput00750, viewModel.getItem_00750().dischargeProperty());
    bindInput(offloadInput01000, viewModel.getItem_01000().dischargeProperty());
    bindInput(offloadInput02000, viewModel.getItem_02000().dischargeProperty());
    bindInput(offloadInput03000, viewModel.getItem_03000().dischargeProperty());
    bindInput(offloadInput04000, viewModel.getItem_04000().dischargeProperty());
    bindInput(offloadInput05000, viewModel.getItem_05000().dischargeProperty());
    bindInput(offloadInput06000, viewModel.getItem_06000().dischargeProperty());
    bindInput(offloadInput07000, viewModel.getItem_07000().dischargeProperty());
    bindInput(offloadInput08000, viewModel.getItem_08000().dischargeProperty());
    bindInput(offloadInput09000, viewModel.getItem_09000().dischargeProperty());
    bindInput(offloadInput10000, viewModel.getItem_10000().dischargeProperty());

    bindInput(porosityCoef, viewModel.porosityCoefficientProperty());
    bindInput(ringHeightTextField, viewModel.ringHeightProperty());

    if (viewModel.getRingNumber() == null) {
      ringNumberChoiceBox.setValue(null);
    } else {
      ringNumberChoiceBox.getSelectionModel().select(viewModel.getRingNumber().toString());
    }

    log.info(String.format("loadDto test from: %s", from));
    log.info(String.format("ringNumber: %s", viewModel.getRingNumber()));
    log.info(String.format("ringHeight: %s", viewModel.getRingHeight()));

    // chart.setData(FXCollections.observableList(List.of(pressureData(),
    // offloadData())));
    redrawChart();
  }

  private final ObservableList<Sample> sampleList = FXCollections.observableArrayList();

  // private class InvertedYAxis extends ValueAxis<Number> {
  //
  // private boolean inversed = true;
  // private double offset = 0D;
  //
  // @Override
  // protected List<Number> calculateMinorTickMarks() {
  // return null;
  // }
  //
  // @Override
  // protected void setRange(Object o, boolean b) {
  //
  // }
  //
  // public void inverse() {
  // inversed = !inversed; // boolean property
  // invalidateRange();
  // requestAxisLayout();
  // }
  //
  // @Override
  // public Number getValueForDisplay(Number displayPosition) {
  // if (inversed)
  // return super.getValueForDisplay(offset - displayPosition);
  // else
  // return super.getValueForDisplay(displayPosition);
  // }
  //
  // @Override
  // public double getDisplayPosition(Double value) {
  // if (inversed)
  // return offset - super.getDisplayPosition(value);
  // else
  // return super.getDisplayPosition(value);
  // }
  //
  // public boolean isInversed() {
  // return inversed;
  // }
  //
  // @Override
  // protected void layoutChildren() {
  // final Side side = getSide();
  // boolean isHorizontal = null == side || side.isHorizontal();
  // double offSetting = isHorizontal ? getWidth() : getHeight();
  //
  // super.layoutChildren();
  // if (inversed) {
  // double prevEnd = isHorizontal ? offSetting + getTickLabelGap() : 0;
  // for (TickMark m : getTickMarks()) {
  // double position = m.getPosition();
  // try {
  // if (0 <= position && position <= offSetting)
  // if (isHorizontal) {
  // m.setTextVisible(position < prevEnd);
  // prevEnd = position - (2 + getTickLabelGap());
  // } else {
  // m.setTextVisible(position > prevEnd);
  // prevEnd = position + (2 + getTickLabelGap());
  // }
  // } catch (Exception ignored) {
  // System.out.println("illegal...?");
  // }
  // }
  // }
  // }
  // @Override
  // protected Object getRange() {
  // return null;
  // }
  //
  // @Override
  // protected List<Double> calculateTickValues(double v, Object o) {
  // return null;
  // }
  //
  // @Override
  // protected String getTickMarkLabel(Double aDouble) {
  // return null;
  // }
  // }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    init();
    loadDto(state.getSample().getLaborNumber(), "init");
    // chart = new LineChart<Number, Number>(new NumberAxis(0D,1.5D,0.125D),
    // (ValueAxis<Number>)new InvertedYAxis());
    chart.setAnimated(false);

    sampleChoiceBox.setValue(state.getSample().getLaborNumber());
  }

  public void onPreviousSampleButtonClick(ActionEvent actionEvent) {
    // int indexOfCurrentSample = sampleList.stream().filter(x ->
    // x.getLaborNumber().equals(state.getSample().getLaborNumber()));
    int indexOfCurrentSample = sampleList.stream().map(Sample::getLaborNumber).toList()
        .indexOf(state.getSample().getLaborNumber());

    int total = sampleList.size();
    int prevIndex = (indexOfCurrentSample - 1 + total) % total;
    Sample nextSample = sampleList.get(prevIndex);

    state.setSample(nextSample);
    sampleChoiceBox.setValue(state.getSample().getLaborNumber());

    loadDto(sampleChoiceBox.getValue(), "prevSample");
  }

  public void onNextSampleButtonClick(ActionEvent actionEvent) {
    int indexOfCurrentSample = sampleList.stream().map(Sample::getLaborNumber).toList()
        .indexOf(state.getSample().getLaborNumber());

    int nextIndex = (indexOfCurrentSample + 1) % sampleList.size();
    Sample nextSample = sampleList.get(nextIndex);

    state.setSample(nextSample);
    sampleChoiceBox.setValue(state.getSample().getLaborNumber());

    loadDto(sampleChoiceBox.getValue(), "nextSample");
  }

  public void onDeformationTableClicked(ActionEvent actionEvent) throws IOException {
    JavaFXCommonMethods.changeScene(actionEvent, ViewsEnum.COMPRESSION_DEFORMATION, applicationContext);
  }

  public void onPorosityTableClicked(ActionEvent actionEvent) throws IOException {
    JavaFXCommonMethods.changeScene(actionEvent, ViewsEnum.COMPRESSION_POROSITY, applicationContext);
  }

  public void onSaveClicked(ActionEvent actionEvent) {
    // viewModel.setLabNumber(state.getSample().getLaborNumber());
    // viewModel.setSample(state.getSample());
    // var ringNumStr= ringNumberChoiceBox.getValue().strip().replace("\"", "\0");
    // var ring = ringService.getByNumber(ringNumStr);
    // viewModel.setRing(ring);
    // viewModel.setRingNumber(Integer.parseInt(ring.getNumber()));
    // viewModel.setRingHeight(ring.getHeight());
    // viewModel.setLabNumber(state.getSample().getLaborNumber());

    log.warn("save clicked");
    if (viewModel != null)
      log.warn(String.format("ringNumber property before: %s", viewModel.getRingNumber()));
    else
      log.fatal("viewmodel is null");
    log.warn(String.format("ringNumber choicebox before: %s", ringNumberChoiceBox.getValue()));
    if (viewModel.getRingNumber() == null) {
      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Form validation");
      alert.setHeaderText("set ring number before committing form");
      alert.initModality(Modality.APPLICATION_MODAL);
      alert.initOwner(stage);
      alert.show();
      return;
    }
    ;
    service.saveData(viewModel);
    loadDto(state.getSample().getLaborNumber(), "saveClicked");
    if (viewModel != null)
      log.warn(String.format("ringNumber property after: %s", viewModel.getRingNumber()));
    log.warn(String.format("ringNumber choicebox after: %s", ringNumberChoiceBox.getValue()));
    redrawChart();
  }

  public void onImportk203Clicked(ActionEvent actionEvent) {
    service.parseK203(viewModel, state.getCurrentProject());
  }

  public void onImportk213Clicked(ActionEvent actionEvent) {
    service.parseK213(viewModel, state.getCurrentProject());
  }

  public void onImportk208LogClicked(ActionEvent actionEvent) {
    service.parseK208(viewModel, state.getCurrentProject());
  }

  public void onImportAnyLogClicked(ActionEvent actionEvent) {
    service.parseLog(viewModel, state.getCurrentProject());
  }

  public void onInputPathsButtonClicked(ActionEvent actionEvent) throws IOException {
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    JavaFXCommonMethods.changeSceneToModalWindow(actionEvent, ViewsEnum.COMPRESSION_PATHS.getPath(), applicationContext,
        stage, ViewsEnum.COMPRESSION_INPUT.getTitle());
  }

  public void ringNumberChoiceBoxSelected(ActionEvent actionEvent) {
    if (ringNumberChoiceBox.getValue() == null) {
      return;
    }

    var num = ringNumberChoiceBox.getValue().replace("\n", "").trim();
    try {
      var ring = ringService.getByNumber(num);
      log.info(String.format("ring set and found: %s, %s", num, ring.getHeight()));
      viewModel.setRingHeight(ring.getHeight() + 0D);
    } catch (NotFoundException ignored) {
      log.info(String.format("ring not found: %s", num));
    }

    viewModel.setRingNumber(Integer.parseInt(num));
  }

  public void onSampleChosen(ActionEvent actionEvent) {
    log.info("sample chosen");
    var newValue = sampleChoiceBox.getValue();
    state.setSample(sampleService.getByLaborNumber(newValue));
    // ringNumberChoiceBox.getSelectionModel().clearSelection();
    loadDto(newValue, "sampleChosen");
    viewModel.setLabNumber(newValue);
    viewModel.setSample(state.getSample());
  }
}
