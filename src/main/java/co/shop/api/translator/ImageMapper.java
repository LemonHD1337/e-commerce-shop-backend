package co.shop.api.translator;

import co.shop.api.dtos.CreateImageDto;
import co.shop.api.dtos.ImageDto;
import co.shop.api.dtos.UpdateImageDto;
import co.shop.api.entities.Image;
import co.shop.api.interfaces.IImageMapper;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper implements IImageMapper {

    private final ProductMapperHelper _helper;

    public ImageMapper(ProductMapperHelper helper) {
        this._helper = helper;
    }

    @Override
    public ImageDto toDto(Image image) {
        var imageDto = new ImageDto();

        imageDto.setImageName(image.getImageName());
        imageDto.setProductId(image.getProduct().getId());

        return imageDto;
    }

    @Override
    public Image fromCreateImageDtoToEntity(CreateImageDto createImageDto) {
        var imageEntity = new Image();
        var product = _helper.mapProduct(createImageDto.getProductId());

        imageEntity.setImageName(createImageDto.getImageName());
        imageEntity.setProduct(product);

        return imageEntity;
    }

    @Override
    public Image fromUpdateImageDtoToEntity(Image imageEntity,UpdateImageDto updateImageDto) {
        var product = _helper.mapProduct(updateImageDto.getProductId());

        imageEntity.setImageName(updateImageDto.getImageName());
        imageEntity.setProduct(product);

        return imageEntity;
    }
}
