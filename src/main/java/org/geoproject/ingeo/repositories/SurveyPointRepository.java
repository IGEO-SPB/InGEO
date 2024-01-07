package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyPointRepository extends JpaRepository<SurveyPoint, Long> {

    List<SurveyPoint> findByProject(Project project, Sort sort);

    SurveyPoint findByPointNumberAndProject(String number, Project project);

    List<SurveyPoint> findByProject(Project project);
}
