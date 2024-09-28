package co.shop.api.services;

import co.shop.api.dtos.imageDto.CreateImageDto;
import co.shop.api.dtos.imageDto.ImageDto;
import co.shop.api.dtos.imageDto.UpdateImageDto;
import co.shop.api.entities.Image;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IImageMapper;
import co.shop.api.interfaces.services.IImageService;
import co.shop.api.repositories.ImageRepository;
import co.shop.api.validation.CentralValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {

    private final ImageRepository _imageRepository;
    private final IImageMapper _imageMapper;
    private final CentralValidator _validator;

    public ImageService(
            ImageRepository imageRepository,
            IImageMapper imageMapper,
            CentralValidator validator
    ) {
        this._imageRepository = imageRepository;
        this._imageMapper = imageMapper;
        this._validator = validator;
    }


    @Override
    public List<ImageDto> getAll() {
        return _imageRepository
                .findAll()
                .stream()
                .map(_imageMapper::toDto)
                .toList();
    }

    @Override
    public ImageDto getById(Long id) {
        return _imageMapper.toDto(getImageEntityById(id));
    }

    @Override
    public ImageDto create(CreateImageDto createImageDto) {
        validate(createImageDto);
        var imageEntity = _imageRepository.save(_imageMapper.fromCreateImageDtoToEntity(createImageDto));
        return _imageMapper.toDto(imageEntity);
    }

    @Override
    public ImageDto update(Long id, UpdateImageDto updateImageDto) {
        validate(updateImageDto);
        var imageEntity = getImageEntityById(id);
        var changedImageEntity =_imageMapper.formUpdateImageDtoToEntity(imageEntity, updateImageDto);
        return _imageMapper.toDto(_imageRepository.save(changedImageEntity));
    }

    @Override
    public ImageDto delete(Long id) {
        var deleteImageEntity = getImageEntityById(id);
        _imageRepository.delete(deleteImageEntity);
        return _imageMapper.toDto(deleteImageEntity);
    }

    private void validate(Object obj) {
        _validator.validate(obj);
    }

    private Image getImageEntityById(Long id){
        return _imageRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Image not found with id: " + id)
                );
    }
}
