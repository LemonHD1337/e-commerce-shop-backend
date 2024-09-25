package co.shop.api.interfaces;

import co.shop.api.dtos.CreateOpinionDto;
import co.shop.api.dtos.OpinionDto;
import co.shop.api.dtos.UpdateOpinionDto;

import java.util.List;

public interface IOpinionService {
    List<OpinionDto> getAll();
    OpinionDto getById(Long id);
    OpinionDto create(CreateOpinionDto createOpinionDto);
    OpinionDto update(Long id, UpdateOpinionDto updateOpinionDto);
    OpinionDto delete(Long id);
}
