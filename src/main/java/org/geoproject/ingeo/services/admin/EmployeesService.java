package com.geoproject.igeo.services.admin;

import com.geoproject.igeo.models.Employee;
import javafx.collections.ObservableList;

import java.util.List;

public interface EmployeesService {
    List<Employee> getAll();

    Employee getById(Long id);

    void create(Employee empl);
}
