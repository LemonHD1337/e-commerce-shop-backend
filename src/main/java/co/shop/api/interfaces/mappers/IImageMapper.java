package co.shop.api.interfaces.mappers;

import co.shop.api.dtos.imageDto.CreateImageDto;
import co.shop.api.dtos.imageDto.ImageDto;
import co.shop.api.entities.Image;

public interface IImageMapper {
    ImageDto toDto(Image image);
    Image fromCreateImageDtoToEntity(CreateImageDto createImageDto);
}
