package co.shop.api.interfaces.mappers;

import co.shop.api.dtos.opinionDto.CreateOpinionDto;
import co.shop.api.dtos.opinionDto.OpinionDto;
import co.shop.api.dtos.opinionDto.UpdateOpinionDto;
import co.shop.api.entities.Opinion;

public interface IOpinionMapper {
    OpinionDto toOpinionDto(Opinion opinion);
    Opinion fromCreateOpinionDtoToOpinionEntity(CreateOpinionDto createOpinionDto);
    Opinion fromUpdateOpinionDtoToOpinionEntity(Opinion opinionFromDb,UpdateOpinionDto updateOpinionDto);
}
