package com.geoproject.igeo.repositories;

import com.geoproject.igeo.models.Project;
import com.geoproject.igeo.models.SurveyPoint;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyPointsRepository extends JpaRepository<SurveyPoint, Long> {

    List<SurveyPoint> findByProject(Project project, Sort sort);

    SurveyPoint findByPointNumberAndProject(String number, Project project);

    List<SurveyPoint> findByProject(Project project);
}
