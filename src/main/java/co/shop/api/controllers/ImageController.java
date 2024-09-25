package co.shop.api.controllers;


import co.shop.api.dtos.CreateImageDto;
import co.shop.api.dtos.ImageDto;
import co.shop.api.dtos.UpdateImageDto;
import co.shop.api.interfaces.IImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ImageDto> createImage(@RequestBody CreateImageDto createImageDto) {
        return ResponseEntity.ok(_imageService.create(createImageDto));
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
