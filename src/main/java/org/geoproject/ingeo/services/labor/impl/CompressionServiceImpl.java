package org.geoproject.ingeo.services.methodViews.impl;

import org.geoproject.ingeo.dto.methodDtos.CompressionDto;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.mapper.laborMethods.CompressionMapper;
import org.geoproject.ingeo.models.*;
import org.geoproject.ingeo.models.classificators.Ring;
import org.geoproject.ingeo.repositories.labor.CompressionPathsRepository;
import org.geoproject.ingeo.repositories.labor.CompressionRepository;
import org.geoproject.ingeo.repositories.classificators.RingRepository;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.methodViews.CompressionService;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@CommonsLog
@AllArgsConstructor
public class CompressionServiceImpl implements CompressionService {
    private final CompressionMapper mapper;
    private final CompressionRepository repository;
    private final CompressionPathsRepository pathsRepository;
    private final RingRepository ringRepository;
    private final SampleService sampleService;

    private void initializePorosityCoefficient(CompressionDto dto, Sample sample) {
        if(sample == null || dto == null) return;
        if(sample.getVoidRatio() == null) dto.setPorosityCoefficient(null);
        else dto.setPorosityCoefficient(sample.getVoidRatio().doubleValue());
    }

    private void populateDtoWithRing(CompressionDto dto, Ring ring) {
        if(ring == null) {
            dto.setRingHeight(null);
            dto.setRingNumber(null);
            return;
        };
        dto.setRingHeight(ring.getHeight().doubleValue());
        dto.setRingNumber(Integer.parseInt(ring.getNumber()));
    }

    public CompressionDto getCompressionForSample(String labNumber) {
        var compression = repository.findByLabNumber(labNumber);
        var sample = sampleService.getByLaborNumber(labNumber);

        if(compression.isEmpty()){
            var res = new CompressionDto();
            res.setSample(sample);
            initializePorosityCoefficient(res, sample);
            return res;
        }

        var res = mapper.modelToDto(compression.get());
        res.setSample(sample);
        populateDtoWithRing(res, compression.get().getRing());
        return res;
    }

    public void setRing(CompressionDto dto, Integer ringNumber) {
        var ring = ringRepository.findById(ringNumber);
        ring.ifPresent(value -> populateDtoWithRing(dto, value));
    }

    private void calculateCompressionItem(CompressionDto.CompressionItemDto item, Double ringHeight, Double porosityCoeff) {
        if(ringHeight == null || ringHeight == 0 || porosityCoeff == null) return;

        if(item.getPressure() != null) {
            var por = porosityCoeff - (1 + porosityCoeff) * item.getPressure() / ringHeight;
            item.setPorosity(por);
        } else item.setPorosity(null);

        if(item.getDischarge() != null) {
            item.setDeformation(item.getDischarge() / ringHeight);
        } else item.setDischarge(null);
    }

    public CompressionDto loadData(String labNumber) {
        var item = repository.findByLabNumber(labNumber);
        if(item.isPresent()) {
            var res = mapper.modelToDto(item.get());
            populateDtoWithRing(res, item.get().getRing());
            return res;
        }
        return null;
    }

    public CompressionDto saveData(CompressionDto dto) {
        calculateCompressionItem(dto.getItem_00000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_00125(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_00250(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_00500(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_00750(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_01000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_02000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_03000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_04000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_05000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_06000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_07000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_08000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_09000(), dto.getRingHeight(), dto.getPorosityCoefficient());
        calculateCompressionItem(dto.getItem_10000(), dto.getRingHeight(), dto.getPorosityCoefficient());

        var dbModel = mapper.dtoToModel(dto);
        var foundModel = repository.findByLabNumber(dbModel.getLabNumber());
        if(foundModel.isPresent()){
            dbModel = foundModel.get();
            mapper.updateModel(dbModel, dto);
        }

        var boolHasRingNumber = dto.getRingNumber() != null;
        //boolean nullStateChanged = !(boolHasRing == boolHasRingNumber);
//        boolean changedRingIfItExisted = boolHasRingNumber && dbModel.getRing().getId() != dto.getRingNumber().longValue();
//        if(boolHasRingNumber) {
        System.out.println((String.format("ringNumber inside service %s", dto.getRingNumber())));
            var ring = ringRepository.findByNumber(dto.getRingNumber().toString());

        if(ring.isPresent()) System.out.println((String.format("ringNumber inside db %s", ring.get().getNumber())));
        else System.out.println("there is no ringNumber");
//        System.out.println((String.format("ringNumber inside service %s", dto.getRingNumber())));
//            ring.ifPresent(value -> log.info(String.format("foundRingHeight %s", value.getHeight())));
        if(ring.isPresent()) dbModel.setRing(ring.get());
//        }

        repository.save(dbModel);

        var res =  mapper.modelToDto(dbModel);
        populateDtoWithRing(res, ring.get());

        return res;
    }

    @Override
    public List<CompressionDto> getCompressionsForProject(Project currentProject) {
        var list = repository.findByProject(currentProject);
        var res = list.stream().map(x -> {
            var dto = mapper.modelToDto(x);
            populateDtoWithRing(dto, x.getRing());
            return dto;
        }).toList();
        return res;
    }

    private void saveNodeValues(String pressure, String deformation, CompressionDto viewModel) {
        var press = Double.parseDouble(pressure);
        var def = Double.parseDouble(deformation);

        var minDiff = Double.MAX_VALUE;
        List<Double> minItems = List.of(0D,0.0125D, 0.0250D, 0.0500D, 0.0750D, 0.1D, 0.2D, 0.3D, 0.4D, 0.5D, 0.6D, 0.7D, 0.8D, 0.9D, 1.0D);
        Double minItem = 0D;

        for(var item: minItems) {
            var diff = Math.abs(item - press);
            if(diff < minDiff) {
                minDiff = diff;
                minItem = item;
            }
        }
        if(minItem == 0) {
            viewModel.getItem_00000().setDeformation(def);
        }
        if(minItem == 0.0125) {
            viewModel.getItem_00125().setDeformation(def);
        }
        if(minItem == 0.025) {
            viewModel.getItem_00250().setDeformation(def);
        }
        if(minItem == 0.05) {
            viewModel.getItem_00500().setDeformation(def);
        }
        if(minItem == 0.075) {
            viewModel.getItem_00750().setDeformation(def);
        }
        if(minItem == 0.1) {
            viewModel.getItem_01000().setDeformation(def);
        }
        if(minItem == 0.2) {
            viewModel.getItem_02000().setDeformation(def);
        }
        if(minItem == 0.3) {
            viewModel.getItem_03000().setDeformation(def);
        }
        if(minItem == 0.4) {
            viewModel.getItem_04000().setDeformation(def);
        }
        if(minItem == 0.5) {
            viewModel.getItem_05000().setDeformation(def);
        }
        if(minItem == 0.6) {
            viewModel.getItem_06000().setDeformation(def);
        }
        if(minItem == 0.7) {
            viewModel.getItem_07000().setDeformation(def);
        }
        if(minItem == 0.8) {
            viewModel.getItem_08000().setDeformation(def);
        }
        if(minItem == 0.9) {
            viewModel.getItem_09000().setDeformation(def);
        }
        if(minItem == 1) {
            viewModel.getItem_10000().setDeformation(def);
        }
    }

    private void processXml(CompressionDto viewModel, Path path) {
        log.info(String.format("processing file %s for lab number %s", path.toString(), viewModel.getLabNumber()));
        var file = path.toFile();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
            // doc.getDocumentElement().normalize();
            var nodes = doc.getElementsByTagName("Param").item(0).getChildNodes();

            String id = "";
            for(var i = 0; i < nodes.getLength(); ++i) {
                if(nodes.item(i).getNodeName().equals("Sample")) id = nodes.item(i).getTextContent();
            }
            if(!viewModel.getLabNumber().equals(id)){
                log.info(String.format("processing file %s for lab number %s and id %s failed", path.toString(), viewModel.getLabNumber(), id));
                return;
            }
            var tests = doc.getElementsByTagName("Test");
            if(tests.getLength() == 0) throw new RuntimeException("No tests found");

            viewModel.getItem_00000().setDeformation(null);
            viewModel.getItem_00125().setDeformation(null);
            viewModel.getItem_00250().setDeformation(null);
            viewModel.getItem_00500().setDeformation(null);
            viewModel.getItem_00750().setDeformation(null);
            viewModel.getItem_01000().setDeformation(null);
            viewModel.getItem_02000().setDeformation(null);
            viewModel.getItem_03000().setDeformation(null);
            viewModel.getItem_04000().setDeformation(null);
            viewModel.getItem_05000().setDeformation(null);
            viewModel.getItem_06000().setDeformation(null);
            viewModel.getItem_07000().setDeformation(null);
            viewModel.getItem_08000().setDeformation(null);
            viewModel.getItem_09000().setDeformation(null);
            viewModel.getItem_10000().setDeformation(null);

            for(int i = 0; i < tests.getLength(); ++i) {
                var test = tests.item(i);
                var testChildren = test.getChildNodes();
                for(var j = 0; j < testChildren.getLength(); ++j) {
                    var node = testChildren.item(j);

                    if(node.getNodeName().equals("StabEnd") && Objects.equals(node.getTextContent(), "true")) {
                        String press= "";
                        String deformation = "";
                        for(var k = 0; k < testChildren.getLength(); ++k) {
                            var nodeChild = testChildren.item(k);
                            if(nodeChild.getNodeName().equals("Press")) {
                                press = nodeChild.getTextContent();
                            } else if(nodeChild.getNodeName().equals("Deformation")) {
                                deformation = nodeChild.getTextContent();
                            }
                        }
                        log.info(String.format("press = %s", press));
                        log.info(String.format("deformation = %s", deformation));
                        if(press != "" && deformation!= "" &&testChildren.getLength() > 0) saveNodeValues(press, deformation, viewModel);
                    }
                }
            }

            saveData(viewModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void parseK203(CompressionDto viewModel, Project proj) {
        var pathObject = pathsRepository.getByProject(proj);
        if(pathObject == null) throw new NotFoundException("Paths object not found");
        var path = pathObject.getPathK203();
        if(path == null) throw new NotFoundException("Path is empty");

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(f -> f.toString().endsWith(".xml"))
                    .forEach(f -> processXml(viewModel, f));
        } catch(Exception ignored) {
            throw new NotFoundException(String.format("203 can't find files in provided path %s", path));
        }
    }

    @Override
    public void parseK208(CompressionDto viewModel, Project proj) {
        var pathObject = pathsRepository.getByProject(proj);
        if(pathObject == null) throw new NotFoundException("Paths object not found");
        var path = pathObject.getPathK208();
        if(path == null) throw new NotFoundException("Path is empty");

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(f -> f.toString().endsWith(".xml"))
                    .forEach(f -> processXml(viewModel, f));
        } catch(Exception ignored) {
            throw new NotFoundException(String.format("208 can't find files in provided path %s", path));
        }

    }

    @Override
    public void parseK213(CompressionDto viewModel, Project proj) {
        var pathObject = pathsRepository.getByProject(proj);
        if(pathObject == null) throw new NotFoundException("Paths object not found");
        var path = pathObject.getPathK213();
        if(path == null) throw new NotFoundException("Path is empty");

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(f -> f.toString().endsWith(".xml"))
                    .forEach(f -> processXml(viewModel, f));
        } catch(IOException ignored) {
            throw new NotFoundException(String.format("213 can't find files in provided path %s", path));
        }
    }

    @Override
    public void parseLog(CompressionDto viewModel, Project proj) {
        var pathObject = pathsRepository.getByProject(proj);
        if(pathObject == null) throw new NotFoundException("Paths object not found");
        var path = pathObject.getPathOther();
        if(path == null) throw new NotFoundException("Path is empty");

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(f -> f.toString().endsWith(".xml"))
                    .forEach(f -> processXml(viewModel, f));
        } catch(Exception ignored) {
            throw new NotFoundException(String.format("Log path can't find files in provided path %s", path));
        }
    }
}
