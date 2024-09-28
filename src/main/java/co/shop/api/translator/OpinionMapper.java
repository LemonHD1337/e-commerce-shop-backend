package co.shop.api.translator;

import co.shop.api.dtos.opinionDto.CreateOpinionDto;
import co.shop.api.dtos.opinionDto.OpinionDto;
import co.shop.api.dtos.opinionDto.UpdateOpinionDto;
import co.shop.api.entities.Opinion;
import co.shop.api.entities.Product;
import co.shop.api.interfaces.mappers.IOpinionMapper;
import org.springframework.stereotype.Component;

@Component
public class OpinionMapper implements IOpinionMapper {
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

        var opinion = new Opinion();
        var product = new Product();

        product.setId(createOpinionDto.getProductId());

        opinion.setComment(createOpinionDto.getComment());
        opinion.setRating(createOpinionDto.getRating());
        opinion.setProduct(product);

        return opinion;
    }

    @Override
    public Opinion fromUpdateOpinionDtoToOpinionEntity(Opinion opinionFromDb, UpdateOpinionDto updateOpinionDto) {
        var product = new Product();
        product.setId(updateOpinionDto.getProductId());

        opinionFromDb.setProduct(product);
        opinionFromDb.setComment(updateOpinionDto.getComment());
        opinionFromDb.setRating(updateOpinionDto.getRating());

        return opinionFromDb;
    }
}
