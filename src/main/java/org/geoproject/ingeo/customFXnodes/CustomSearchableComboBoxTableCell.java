package org.geoproject.ingeo.customFXnodes;

import jakarta.annotation.Nullable;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.StringConverter;
import org.apache.commons.lang.StringUtils;
import org.controlsfx.control.SearchableComboBox;
import org.geoproject.ingeo.controllers.functionalInterfaces.Description;
import org.geoproject.ingeo.controllers.functionalInterfaces.GetComboBoxValue;
import org.geoproject.ingeo.controllers.functionalInterfaces.Settable;

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
public class CustomSearchableComboBoxTableCell<E, Y> extends TableCell<E, ComboBox<Y>> {

    private final ObservableList<Y> observableDtoTableViewList;
    private final TableColumn<E, ComboBox<Y>> column;
    private final ObservableList<E> observableDtoComboBoxList;
    private final Double columnPixelGap;
    Description<Y> objectDescription;
    GetComboBoxValue<E, Y> getComboBoxValue;
    Settable<E, Y> settable;

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
     * @param settable           Метод, обернутый в функциональный интерфейс SetComboBoxValue<E, Y> (параметризован классами E и Y).
     *                                   Сеттит экземпляр класса Y (параметризующий ComboBox) в соответствующее поле
     *                                   класса E (параметризующего TableView).
     *                                   В качестве метода в лямбде передается сеттер того поля в классе E, которое
     *                                   отвечает за класс Y.
     */
    public CustomSearchableComboBoxTableCell(ObservableList<Y> observableDtoTableViewList,
                                             TableColumn<E, ComboBox<Y>> column,
                                             ObservableList<E> observableDtoComboBoxList,
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
     * @param item The new item for the cell.
     * @param empty whether or not this cell represents data from the list. If it
     *        is empty, then it does not represent any domain data, but is a cell
     *        being used to render an "empty" row.
     */
    @Override
    public void updateItem(ComboBox<Y> item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            var box = createBox();
            box.setPrefWidth(column.getWidth());
            setGraphic(box);

            column.widthProperty().addListener((o, ow, newWidth) -> box.setMinWidth((Double) newWidth - columnPixelGap));

            if (Objects.isNull(getComboBoxValue.getValue(observableDtoComboBoxList.get(getIndex())))) {
                box.setValue(null);
            } else {
                box.setValue(getComboBoxValue.getValue(observableDtoComboBoxList.get(getIndex())));
            }
        }
    }

    /**
     * Метод создания экземпляра SearchableComboBox, который в методе updateItem() помещается на UI в ячейки
     * соответствующей колонки таблицы
     *
     * @return Экземпляр SearchableComboBox
     */
    private ComboBox<Y> createBox() {
        var box = new SearchableComboBox<Y>();
        box.getItems().addAll(observableDtoTableViewList);
        box.setConverter(converter);

        box.setOnAction(event -> {

            if (box.getParent() != null) {
                var tableRow = ((TableCell<E, ComboBox<Y>>) box.getParent()).getTableRow();

                var tableDto = observableDtoComboBoxList.get(tableRow.getIndex());
                settable.setValue(tableDto, box.getValue());
            }
        });

        return box;
    }

    /**
     * Метод создания конвертера для определения отображения элемента выпадающего списка.
     * Сделано вместо определения метода toString() в классе, которым параметризован ComboBox.
     *
     * Здесь в методе toString(Y object) можно в принципе прописать любое возвращаемое значение - оно будет показано на UI.
     * В текущей реализации используется метод, возвращающий значение поля Y класса (оборачивается в функциональный интерфейс
     * для передачи в виде лямбды при создании экземпляра TableCell).
     *
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
