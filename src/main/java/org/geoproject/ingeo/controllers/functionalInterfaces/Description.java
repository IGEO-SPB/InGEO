package org.geoproject.ingeo.controllers.functionalInterfaces;

@FunctionalInterface
public interface Description<Y> {

    String getDescription(Y object);
}
