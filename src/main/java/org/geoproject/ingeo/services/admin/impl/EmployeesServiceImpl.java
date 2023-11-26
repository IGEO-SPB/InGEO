package org.geoproject.ingeo.services.admin.impl;

import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.models.Employee;
import org.geoproject.ingeo.repositories.EmployeesRepository;
import org.geoproject.ingeo.services.admin.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;

    public List<Employee> getAll() {
        return employeesRepository.findAll();
    }

    public void create(Employee empl) {
        employeesRepository.save(empl);
    }

    public Employee getById(Long id) {
        return employeesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Сотрудник не найден"));
    }

}
