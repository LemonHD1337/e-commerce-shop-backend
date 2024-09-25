package co.shop.api.interfaces;

import co.shop.api.dtos.CreateImageDto;
import co.shop.api.dtos.ImageDto;
import co.shop.api.dtos.UpdateImageDto;
import co.shop.api.entities.Image;

public interface IImageMapper {
    ImageDto toDto(Image image);
    Image fromCreateImageDtoToEntity(CreateImageDto createImageDto);
    Image fromUpdateImageDtoToEntity(Image imageEntity,UpdateImageDto updateImageDto);
}
