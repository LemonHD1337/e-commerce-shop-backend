package co.shop.api.controllers;


import co.shop.api.dtos.imageDto.CreateImageDto;
import co.shop.api.dtos.imageDto.ImageDto;
import co.shop.api.dtos.imageDto.UpdateImageDto;
import co.shop.api.interfaces.services.IImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    private final IImageService _imageService;

    public ImageController(IImageService imageService) {
        this._imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> getAll() {
        return ResponseEntity.ok(_imageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(_imageService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ImageDto> createImage(@RequestBody CreateImageDto createImageDto, HttpServletRequest request) {
        var createdImage = _imageService.create(createImageDto);

        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}").buildAndExpand(createdImage.getId()).toUri();

        return ResponseEntity.created(location).body(createdImage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageDto> updateImage(@PathVariable Long id, @RequestBody UpdateImageDto updateImageDto) {
        return ResponseEntity.ok(_imageService.update(id, updateImageDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        _imageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
