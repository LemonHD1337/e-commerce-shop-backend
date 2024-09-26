package co.shop.api.interfaces.services;

import co.shop.api.dtos.opinionDto.CreateOpinionDto;
import co.shop.api.dtos.opinionDto.OpinionDto;
import co.shop.api.dtos.opinionDto.UpdateOpinionDto;

import java.util.List;

public interface IOpinionService {
    List<OpinionDto> getAll();
    OpinionDto getById(Long id);
    OpinionDto create(CreateOpinionDto createOpinionDto);
    OpinionDto update(Long id, UpdateOpinionDto updateOpinionDto);
    OpinionDto delete(Long id);
}
