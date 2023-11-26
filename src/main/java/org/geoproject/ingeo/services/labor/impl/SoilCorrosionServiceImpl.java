package org.geoproject.ingeo.services.methodViews.impl;

import org.geoproject.ingeo.dto.PrintInfoDto;
import org.geoproject.ingeo.dto.methodDtos.SoilCorrosionInputDto;
import org.geoproject.ingeo.dto.methodDtos.SoilCorrosionResultDto;
import org.geoproject.ingeo.mapper.laborMethods.SoilCorrosionInputMapper;
import org.geoproject.ingeo.mapper.laborMethods.SoilCorrosionResultMapper;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.SoilCorrosionInput;
import org.geoproject.ingeo.models.labor.SoilCorrosionResult;
import org.geoproject.ingeo.repositories.labor.SoilCorrosionInputRepository;
import org.geoproject.ingeo.repositories.labor.SoilCorrosionResultRepository;
import org.geoproject.ingeo.services.methodViews.SoilCorrosionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SoilCorrosionServiceImpl implements SoilCorrosionService {
    private final SoilCorrosionInputMapper inputMapper;
    private final SoilCorrosionInputRepository inputRepository;
    private final SoilCorrosionResultMapper resultMapper;
    private final SoilCorrosionResultRepository resultRepository;
    @Override
    public SoilCorrosionInputDto createNewSoilCorrosion(Sample sp, String labNumber) {
        var model = new SoilCorrosionInput();
        model.setLabNumber(labNumber);
        model.setSample(sp);
        inputRepository.save(model);

        return inputMapper.modelToDto(model);
    }

    @Override
    public SoilCorrosionInputDto updateSoilCorrosion(Long id, SoilCorrosionInputDto newData) {
        newData.setId(id);
        inputRepository.save(inputMapper.dtoToModel(newData));
        return newData;
    }

    @Override
    public void deleteSoilCorrosion(Long id) {
        inputRepository.deleteById(id);
    }

    @Override
    public SoilCorrosionInputDto calculateResistance(SoilCorrosionInputDto dto) {
        var current_mA = dto.getCurrent();
        var current_A = current_mA / 1000;
        var voltage_V = dto.getVoltage();
        try {
            dto.setResistance(voltage_V / current_A);
        } catch (ArithmeticException ignored){
        }
        inputRepository.save(inputMapper.dtoToModel(dto));
        return dto;
    }

    @Override
    public List<SoilCorrosionResultDto> calculateAverageUesgAndPkt(Sample sample, String labNumber) {
        List<SoilCorrosionInput> inputs = inputRepository.findAllBySample(sample);
        List<SoilCorrosionResult> results = resultRepository.findAllBySample(sample);

        if(results.stream().noneMatch(x-> Objects.equals(x.getLabNumber(), labNumber))) {
            var res = new SoilCorrosionResult();
            res.setLabNumber(labNumber);
            res.setSample(sample);
            results.add(res);
        }
        results.forEach(result -> {
            var labNumberInputs = inputs.stream().filter(input -> {
                //var sameSample = input.getSample() == result.getSample();
                var sameLabNumber = Objects.equals(input.getLabNumber(), result.getLabNumber());
                return sameLabNumber;
            }).toList();

            float sum, total, avg;


            sum = labNumberInputs.stream().map(SoilCorrosionInput::getResistance).reduce(0F, Float::sum);
            total = labNumberInputs.size();

            if(total != 0) {
                avg = sum / total;
                result.setUesg(avg * 0.035F);
            }

            sum = labNumberInputs.stream().map(SoilCorrosionInput::getCathodeCurrent).reduce(0F, Float::sum);
            total = labNumberInputs.size();
            if(total != 0) {
                avg = sum / total;
                result.setPkt(avg * 1000);
            }

            result.setSample(sample);
        });

        results.forEach(resultRepository::save);

        return results.stream().map(resultMapper::modelToDto).toList();
    }

    @Override
    public SoilCorrosionResultDto calculateCorrosionAggressionDegree(SoilCorrosionResultDto dto) {
        var uesg = dto.getUesg();

        if(uesg > 50) dto.setCorrAggrDegreeUesg("Низкая");
        else if(uesg >= 20) dto.setCorrAggrDegreeUesg("Средняя");
        else if(uesg > 0) dto.setCorrAggrDegreeUesg("Высокая");

        var pkt = Math.round(dto.getPkt()/10) / 100;

        if(pkt > 0.2) dto.setCorrAggrDegreePkt("Высокая");
        else if(pkt >= 0.05) dto.setCorrAggrDegreePkt("Средняя");
        else if(pkt > 0) dto.setCorrAggrDegreePkt("Низкая");

        resultRepository.save(resultMapper.dtoToModel(dto));
        return dto;
    }

    @Override
    public File printCorrosionAggressionTable(PrintInfoDto printInfoDto, Sample sp) {
        throw new NotImplementedException();
    }

    @Override
    public List<SoilCorrosionInputDto> getInputsBySample(Sample sp) {
        return inputRepository.findAllBySample(sp).stream().map(inputMapper::modelToDto).toList();
    }

    @Override
    public List<SoilCorrosionResultDto> getOrCreateResultBySample(Sample sp) {
        return resultRepository.findAllBySample(sp).stream().map(resultMapper::modelToDto).toList();
    }
}
