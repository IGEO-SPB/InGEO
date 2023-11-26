package com.geoproject.igeo.repositories;

import com.geoproject.igeo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {
    Project findByContractNumber(String contractNumber);
}
