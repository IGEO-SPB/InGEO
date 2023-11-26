package com.geoproject.igeo.services.admin.impl;

import com.geoproject.igeo.exceptions.NotFoundException;
import com.geoproject.igeo.models.Employee;
import com.geoproject.igeo.repositories.EmployeesRepository;
import com.geoproject.igeo.services.admin.EmployeesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
