package co.shop.api.services;

import co.shop.api.exception.StorageException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageService implements co.shop.api.interfaces.services.StorageService {


    private final Path rootLocation;

    public StorageService() {
        this.rootLocation = Path.of("uploads");
    }


    @Override
    public String store(MultipartFile file) {
        try{

            String uuid = UUID.randomUUID().toString();
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String extension = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = uuid + extension;

            Thumbnails.of(file.getInputStream())
                    .size(300, 300)
                    .toFile(rootLocation.resolve(newFileName).toFile());

            return newFileName;
        }catch (Exception e){
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public void update(String filename, MultipartFile file) {
        if(filename.trim().isEmpty()) throw new StorageException("Filename cannot be empty");

        try{
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(filename)
            ).normalize().toAbsolutePath();

            Thumbnails.of(file.getInputStream())
                    .size(800, 600)
                    .toFile(rootLocation.resolve(destinationFile).toFile());

        }catch (Exception e){
            throw new StorageException("Failed to update file", e);
        }
    }

    @Override
    public void delete(String filename) {
        if(filename.trim().isEmpty()) throw new StorageException("Filename cannot be empty");

        try{
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(filename)
            ).normalize().toAbsolutePath();

            Files.delete(destinationFile);
        }catch (IOException e){
            throw new StorageException("Failed to delete file", e);
        }
    }

    @Override
    public byte[] loadAsResource(String filename) {
        try{
            return Files.readAllBytes(load(filename));
        }catch (IOException e){
            throw new StorageException("Failed to load file", e);
        }
    }
}
