package co.shop.api.interfaces;

import co.shop.api.dtos.CreateOpinionDto;
import co.shop.api.dtos.OpinionDto;
import co.shop.api.dtos.UpdateOpinionDto;
import co.shop.api.entities.Opinion;

public interface IOpinionMapper {
    OpinionDto toOpinionDto(Opinion opinion);
    Opinion fromCreateOpinionDtoToOpinionEntity(CreateOpinionDto createOpinionDto);
    Opinion fromUpdateOpinionDtoToOpinionEntity(Opinion opinionEntity,UpdateOpinionDto updateOpinionDto);
}
