package co.shop.api.translator;

import co.shop.api.dtos.imageDto.CreateImageDto;
import co.shop.api.dtos.imageDto.ImageDto;
import co.shop.api.entities.Image;
import co.shop.api.interfaces.mappers.IImageMapper;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper implements IImageMapper {

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

        imageEntity.setImageName(createImageDto.getImageName());
        imageEntity.setProduct(createImageDto.getProduct());

        return imageEntity;
    }
}
