package co.shop.api.controllers;


import co.shop.api.dtos.imageDto.CreateImageDto;
import co.shop.api.dtos.imageDto.ImageDto;
import co.shop.api.interfaces.services.IImageService;
import co.shop.api.interfaces.services.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    private final IImageService _imageService;
    private final StorageService _storageService;

    public ImageController(IImageService imageService, StorageService storageService) {
        this._imageService = imageService;
        this._storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> getAll() {
        return ResponseEntity.ok(_imageService.getAll());
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<ImageDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(_imageService.getById(id));
    }

    @GetMapping("/getByFilename/{filename}")
    public ResponseEntity<byte[]> getByFilename(@PathVariable String filename) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(_storageService.loadAsResource(filename));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageDto> createImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") Long productId,
            HttpServletRequest request
    ) {
        var filename = _storageService.store(file);

        var createImageDto = new CreateImageDto();
        createImageDto.setProductId(productId);
        createImageDto.setImageName(filename);

        var createdImage = _imageService.create(createImageDto);

        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}").buildAndExpand(createdImage.getId()).toUri();

        return ResponseEntity.created(location).body(createdImage);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/{id}")
    public ResponseEntity<Void> updateImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        var imageEntity = _imageService.getById(id);

        _storageService.update(imageEntity.getImageName(), file);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        var deletedImage = _imageService.delete(id);
        _storageService.delete(deletedImage.getImageName());
        return ResponseEntity.noContent().build();
    }
}
