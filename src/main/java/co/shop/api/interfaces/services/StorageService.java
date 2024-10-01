package co.shop.api.interfaces.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    String store(MultipartFile file);
    Path load(String filename);
    void update(String filename, MultipartFile file);
    byte[] loadAsResource(String filename);
    void delete(String filename);
}
