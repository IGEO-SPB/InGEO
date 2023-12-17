package org.geoproject.ingeo.controllers.functionalInterfaces;

@FunctionalInterface
public interface GetComboBoxValue<E, Y> {

    Y getValue(E object);
}
