package co.shop.api.services;

import co.shop.api.dtos.CreateImageDto;
import co.shop.api.dtos.ImageDto;
import co.shop.api.dtos.UpdateImageDto;
import co.shop.api.exception.EmptyRequestBodyException;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.IImageMapper;
import co.shop.api.interfaces.IImageService;
import co.shop.api.repositories.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {

    private final ImageRepository _imageRepository;
    private final IImageMapper _imageMapper;


    public ImageService(ImageRepository imageRepository, IImageMapper imageMapper) {
        this._imageRepository = imageRepository;
        this._imageMapper = imageMapper;
    }


    @Override
    public List<ImageDto> getAll() {

        var images =  _imageRepository
                .findAll()
                .stream()
                .map(_imageMapper::toDto)
                .toList();

        return images;
    }

    @Override
    public ImageDto getById(Long id) {
        return _imageRepository
                .findById(id)
                .map(_imageMapper::toDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Image not found with id: " + id)
                );
    }

    @Override
    public ImageDto create(CreateImageDto createImageDto) {
        if(createImageDto == null) throw new EmptyRequestBodyException("data is missing");

        var imageEntity = _imageRepository.save(_imageMapper.fromCreateImageDtoToEntity(createImageDto));

        return _imageMapper.toDto(imageEntity);
    }

    @Override
    public ImageDto update(Long id, UpdateImageDto updateImageDto) {
        if(updateImageDto == null) throw new EmptyRequestBodyException("data is missing");

        var imageEntity = _imageRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Image not found with id: " + id)
                );

        var changedImageEntity = _imageMapper.fromUpdateImageDtoToEntity(imageEntity, updateImageDto);

        _imageRepository.save(changedImageEntity);

        return _imageMapper.toDto(changedImageEntity);
    }

    @Override
    public ImageDto delete(Long id) {
        var deleteImageEntity = _imageRepository
                .findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Image not found with id: " + id)
                );

        _imageRepository.delete(deleteImageEntity);

        return _imageMapper.toDto(deleteImageEntity);
    }
}
