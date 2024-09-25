package co.shop.api.interfaces;

import co.shop.api.dtos.CreateImageDto;
import co.shop.api.dtos.ImageDto;
import co.shop.api.dtos.UpdateImageDto;

import java.util.List;

public interface IImageService {
    List<ImageDto> getAll();
    ImageDto getById(Long id);
    ImageDto create(CreateImageDto createImageDto);
    ImageDto update(Long id, UpdateImageDto updateImageDto);
    ImageDto delete(Long id);
}
