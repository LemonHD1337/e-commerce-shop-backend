package co.shop.api.services;

import co.shop.api.dtos.imageDto.CreateImageDto;
import co.shop.api.dtos.imageDto.ImageDto;
import co.shop.api.dtos.imageDto.UpdateImageDto;
import co.shop.api.exception.EmptyRequestBodyException;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IImageMapper;
import co.shop.api.interfaces.services.IImageService;
import co.shop.api.repositories.ImageRepository;
import co.shop.api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {

    private final ImageRepository _imageRepository;
    private final ProductRepository _productRepository;
    private final IImageMapper _imageMapper;


    public ImageService(ImageRepository imageRepository, IImageMapper imageMapper, ProductRepository productRepository) {
        this._imageRepository = imageRepository;
        this._imageMapper = imageMapper;
        this._productRepository = productRepository;
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

        var product = _productRepository
                .findById(createImageDto.getProductId())
                .orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + createImageDto.getProductId())
                );

        createImageDto.setProduct(product);

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

        var productEntity = _productRepository
                .findById(updateImageDto.getProductId())
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Product not found with id: " + updateImageDto.getProductId())
                );


        imageEntity.setImageName(updateImageDto.getImageName());
        imageEntity.setProduct(productEntity);

        var changedImageEntity = _imageRepository.save(imageEntity);

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
