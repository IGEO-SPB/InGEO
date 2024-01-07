package org.geoproject.ingeo.customFXnodes;

import jakarta.annotation.Nullable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import org.apache.commons.lang.StringUtils;
import org.controlsfx.control.SearchableComboBox;
import org.geoproject.ingeo.controllers.functionalInterfaces.Description;
import org.geoproject.ingeo.controllers.functionalInterfaces.GetComboBoxValue;
import org.geoproject.ingeo.controllers.functionalInterfaces.Settable;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;

import java.util.Objects;


/**
 * Реализация ячейки (TableCell) в TableView, содержащей SearchableComboBox (из библиотеки Controlsfx).
 * В реализации переопределен метод родительского класса - updateItem(T item, boolean empty).
 * Поскольку в родительском классе еще много методов, отвечающих за разные аспекты поведения ячеек,
 * при необходимости изменения какого-то поведения для ComboBox следует наследовать настоящий класс,
 * а в наследнике переопределить необходимые методы.
 *
 * @param <E> Класс, параметризующий таблицу TableView (строки в таблице - экземпляры этого класса)
 * @param <Y> Класс, параметризующий выпадающий список (ComboBox, или, в данном случае, его наследник - SearchableComboBox).
 *            Элементы выпадающего списка - экземпляры этого класса.
 */
public class CustomSearchableComboBoxTableCell<E, Y> extends TableCell<E, SearchableComboBox<Y>> {
//public class CustomSearchableComboBoxTableCell<E, Y> extends TableCell<E, Y> {

    private final ObservableList<E> observableDtoTableViewList;
    private final TableColumn<E, SearchableComboBox<Y>> column;
    //    private final TableColumn<E, Y> column;
    private final ObservableList<Y> observableDtoComboBoxList;
    private final Double columnPixelGap;
    Description<Y> objectDescription;
    GetComboBoxValue<E, Y> getComboBoxValue;
    Settable<E, Y> settable;

    private SearchableComboBox<Y> box;


    private static final Double ZERO_PIXEL_GAP = 0D;

    private final StringConverter<Y> converter;

    {
        converter = getStringConverter();
    }

    /**
     * Конструктор CustomSearchableComboBoxTableCell
     *
     * @param observableDtoTableViewList Список (ObservableList) экземпляров из таблицы TableView, параметризованный соответствующей сущностью
     * @param column                     Колонка в таблице TableView, параметризованная соответствующими классами
     * @param observableDtoComboBoxList  Список (ObservableList) экземпляров класса, предназначенного для вывода в выпадающем списке
     * @param columnPixelGap             Отступ между правым краем ComboBox и границей колонки - необходимо для красоты форматирования.
     *                                   Необязательный параметр.
     * @param objectDescription          Метод, обернутый в функциональный интерфейс Description<Y> (параметризован классом Y).
     *                                   В качестве метода в лямбде передается геттер того поля в классе Y (т.е. классе,
     *                                   параметризующем ComboBox), которое отвечает за названия пунктов в выпадающем списке
     * @param getComboBoxValue           Метод, обернутый в функциональный интерфейс GetComboBoxValue<E, Y> (параметризован классами E и Y).
     *                                   Возвращает экземпляр класса Y (параметризующий ComboBox).
     *                                   В качестве метода в лямбде передается геттер того поля в классе E (т.е. классе,
     *                                   параметризующем TableView), которое отвечает за класс Y.
     * @param settable                   Метод, обернутый в функциональный интерфейс SetComboBoxValue<E, Y> (параметризован классами E и Y).
     *                                   Сеттит экземпляр класса Y (параметризующий ComboBox) в соответствующее поле
     *                                   класса E (параметризующего TableView).
     *                                   В качестве метода в лямбде передается сеттер того поля в классе E, которое
     *                                   отвечает за класс Y.
     */
    public CustomSearchableComboBoxTableCell(ObservableList<E> observableDtoTableViewList,
                                             TableColumn<E, SearchableComboBox<Y>> column,
//                                             TableColumn<E, Y> column,
                                             ObservableList<Y> observableDtoComboBoxList,
                                             @Nullable Double columnPixelGap,
                                             Description<Y> objectDescription,
                                             GetComboBoxValue<E, Y> getComboBoxValue,
                                             Settable<E, Y> settable
    ) {
        this.observableDtoTableViewList = observableDtoTableViewList;
        this.column = column;
        this.observableDtoComboBoxList = observableDtoComboBoxList;
        this.columnPixelGap = Objects.isNull(columnPixelGap) ? ZERO_PIXEL_GAP : columnPixelGap;
        this.objectDescription = objectDescription;
        this.getComboBoxValue = getComboBoxValue;
        this.settable = settable;
    }

    /**
     * Переопределенный метод родительского класса TableCell.
     *
     * @param item  The new item for the cell.
     * @param empty whether or not this cell represents data from the list. If it
     *              is empty, then it does not represent any domain data, but is a cell
     *              being used to render an "empty" row.
     */
    @Override
    public void updateItem(SearchableComboBox<Y> item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            if (getIndex() < observableDtoTableViewList.size()) {
                var box = createBox();
                box.setPrefWidth(column.getWidth());
                setGraphic(box);

                column.widthProperty().addListener((o, ow, newWidth) -> box.setMinWidth((Double) newWidth - columnPixelGap));

                System.out.println("Current index: " + getIndex());
                System.out.println("List size: " + observableDtoTableViewList.size());

                if (Objects.isNull(getComboBoxValue.getValue(observableDtoTableViewList.get(getIndex())))) {
                    box.setValue(null);
                } else {
                    Y value = getComboBoxValue.getValue(observableDtoTableViewList.get(getIndex()));

                    box.setValue(value);
                }
            }

        }

    }

    @Override
    public void commitEdit(SearchableComboBox<Y> newValue) {
        super.commitEdit(newValue);

        System.out.println("=== NEW VALUE ===");
        System.out.println(newValue);
        if (Objects.nonNull(newValue)) {
            if (newValue.getValue() instanceof EgeDto) {
                System.out.println(((EgeDto) newValue.getValue()).getEgeNumber());
            }
        }
    }

    /**
     * Метод создания экземпляра SearchableComboBox, который в методе updateItem() помещается на UI в ячейки
     * соответствующей колонки таблицы
     *
     * @return Экземпляр SearchableComboBox
     */
    private SearchableComboBox<Y> createBox() {
        box = new SearchableComboBox<Y>();
        box.getItems().addAll(observableDtoComboBoxList);
        box.setConverter(converter);

        box.setOnShowing(event -> System.out.println("showing - " + box.isShowing()));
        box.setOnShown(event -> System.out.println("shown - " + box.isShowing()));
        box.setOnHiding(event -> {

        });
        box.setOnHidden(event -> System.out.println("hidden - " + box.isShowing()));


        box.setOnAction(event -> {

            System.out.println("ACTION");

            if (box.getParent() != null) {
                System.out.println("SET VALUE");

                var tableRow = ((TableCell<E, ComboBox<Y>>) box.getParent()).getTableRow();

                var tableDto = observableDtoTableViewList.get(tableRow.getIndex());
                settable.setValue(tableDto, box.getValue());

            }


//            this.commitEdit(box);

            System.out.println("isEditing()");
            System.out.println(isEditing());

//            super.commitEdit(box);

            final TableView<E> table = getTableView();
            if (table != null) {
                // Inform the TableView of the edit being ready to be committed.

                TablePosition position = new TablePosition(getTableView(), getTableRow().getIndex(), getTableColumn());

                if (position != null) {

                    TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(
                            table,
//                        table.getEditingCell(),
                            position,
                            TableColumn.editCommitEvent(),
                            box.getValue()
                    );

//                    TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent();


                    Event.fireEvent(getTableColumn(), editEvent);
                }
            }

        });

        return box;
    }

    /**
     * Метод создания конвертера для определения отображения элемента выпадающего списка.
     * Сделано вместо определения метода toString() в классе, которым параметризован ComboBox.
     * <p>
     * Здесь в методе toString(Y object) можно в принципе прописать любое возвращаемое значение - оно будет показано на UI.
     * В текущей реализации используется метод, возвращающий значение поля Y класса (оборачивается в функциональный интерфейс
     * для передачи в виде лямбды при создании экземпляра TableCell).
     * <p>
     * Реализация метода fromString(String string) не переопределена - получаемое строковое значение должно быть преобразовано
     * в объект класса Y. Однако возвращаемое строковое значение может быть не уникальным, поэтому искать по нему объект
     * в базе данных некорректно.
     *
     * @return Экземпляр анонимного класса StringConverter.
     */
    private StringConverter<Y> getStringConverter() {
        return new StringConverter<Y>() {
            @Override
            public String toString(Y object) {

                if (Objects.nonNull(object)) {
                    return objectDescription.getDescription(object);
                }

                return StringUtils.EMPTY;
            }

            @Override
            public Y fromString(String string) {
                return null;
            }
        };
    }
}
