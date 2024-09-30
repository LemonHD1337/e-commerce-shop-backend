package co.shop.api.interfaces.services;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    String store(MultipartFile file);
    Path load(String filename);
    void update(String filename, MultipartFile file);
    Resource loadAsResource(String filename);
    void delete(String filename);
}
