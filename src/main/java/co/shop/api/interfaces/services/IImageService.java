package co.shop.api.interfaces.services;

import co.shop.api.dtos.imageDto.CreateImageDto;
import co.shop.api.dtos.imageDto.ImageDto;
import co.shop.api.dtos.imageDto.UpdateImageDto;

import java.util.List;

public interface IImageService {
    List<ImageDto> getAll();
    ImageDto getById(Long id);
    ImageDto create(CreateImageDto createImageDto);
    ImageDto update(Long id, UpdateImageDto updateImageDto);
    ImageDto delete(Long id);
}
