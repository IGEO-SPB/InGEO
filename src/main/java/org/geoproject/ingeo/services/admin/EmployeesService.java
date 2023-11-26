package org.geoproject.ingeo.services.admin;

import org.geoproject.ingeo.models.Employee;

import java.util.List;

public interface EmployeesService {
    List<Employee> getAll();

    Employee getById(Long id);

    void create(Employee empl);
}
