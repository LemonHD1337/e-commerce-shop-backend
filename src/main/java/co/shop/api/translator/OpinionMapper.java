package co.shop.api.translator;

import co.shop.api.dtos.CreateOpinionDto;
import co.shop.api.dtos.OpinionDto;
import co.shop.api.dtos.UpdateOpinionDto;
import co.shop.api.entities.Opinion;
import co.shop.api.interfaces.IOpinionMapper;

public class OpinionMapper implements IOpinionMapper {

    private final ProductMapperHelper _helper;

    public OpinionMapper(ProductMapperHelper helper) {
        this._helper = helper;
    }

    @Override
    public OpinionDto toOpinionDto(Opinion opinion) {
        var opinionDto = new OpinionDto();

        opinionDto.setId(opinion.getId());
        opinionDto.setComment(opinion.getComment());
        opinionDto.setRating(opinion.getRating());
        opinionDto.setProductId(opinion.getProduct().getId());

        return opinionDto;
    }

    @Override
    public Opinion fromCreateOpinionDtoToOpinionEntity(CreateOpinionDto createOpinionDto) {
        var product = _helper.mapProduct(createOpinionDto.getProductId());

        var opinion = new Opinion();

        opinion.setComment(createOpinionDto.getComment());
        opinion.setRating(createOpinionDto.getRating());
        opinion.setProduct(product);

        return opinion;
    }

    @Override
    public Opinion fromUpdateOpinionDtoToOpinionEntity(Opinion opinionEntity,UpdateOpinionDto updateOpinionDto) {
        var product = _helper.mapProduct(updateOpinionDto.getProductId());

        opinionEntity.setComment(updateOpinionDto.getComment());
        opinionEntity.setRating(updateOpinionDto.getRating());
        opinionEntity.setProduct(product);

        return opinionEntity;
    }
}
