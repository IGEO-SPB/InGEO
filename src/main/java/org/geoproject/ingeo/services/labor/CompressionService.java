package org.geoproject.ingeo.services.methodViews;

import org.geoproject.ingeo.dto.methodDtos.CompressionDto;
import org.geoproject.ingeo.models.Project;

import java.util.List;

public interface CompressionService {
    public CompressionDto getCompressionForSample(String labNumber);

    public CompressionDto loadData(String labNumber);
    public CompressionDto saveData(CompressionDto dto);

    List<CompressionDto> getCompressionsForProject(Project currentProject);
    void parseK203(CompressionDto viewModel, Project proj);
    void parseK208(CompressionDto viewModel, Project proj);
    void parseK213(CompressionDto viewModel, Project proj);
    void parseLog(CompressionDto viewModel, Project proj);
}
