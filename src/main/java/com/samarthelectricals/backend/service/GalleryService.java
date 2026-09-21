package com.samarthelectricals.backend.service;

import com.samarthelectricals.backend.entity.Gallery;
import com.samarthelectricals.backend.repository.GalleryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class GalleryService {

    private final GalleryRepository galleryRepository;

    private final Path uploadDirectory =
            Paths.get("uploads/gallery");

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;

        try {
            Files.createDirectories(uploadDirectory);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not create gallery upload directory", e
            );
        }
    }

    // Get all gallery images
    public List<Gallery> getAllImages() {
        return galleryRepository.findAll();
    }

    // Get image by ID
    public Gallery getImageById(Long id) {

        return galleryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Gallery image not found"));
    }

    // Upload image
    public Gallery uploadImage(
            MultipartFile file,
            String caption,
            String description) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Please select an image");
        }

        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null ||
                originalFileName.trim().isEmpty()) {

            throw new RuntimeException("Invalid file name");
        }

        // Get file extension
        String extension = "";

        int dotIndex = originalFileName.lastIndexOf(".");

        if (dotIndex >= 0) {
            extension = originalFileName.substring(dotIndex);
        }

        // Generate unique file name
        String newFileName =
                UUID.randomUUID().toString() + extension;

        Path filePath =
                uploadDirectory.resolve(newFileName);

        try {

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to save image", e
            );
        }

        // Create database record
        Gallery gallery = new Gallery();

        gallery.setFileName(newFileName);

        gallery.setFileUrl(
                "/uploads/gallery/" + newFileName
        );

        gallery.setCaption(
                caption != null ? caption : ""
        );

        gallery.setDescription(
                description != null ? description : ""
        );

        return galleryRepository.save(gallery);
    }

    // Delete image
    public void deleteImage(Long id) {

        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Gallery image not found"));

        // Delete physical image
        try {

            Path filePath =
                    Paths.get(gallery.getFileUrl()
                            .replaceFirst("^/", ""));

            Files.deleteIfExists(filePath);

        } catch (IOException e) {

            System.err.println(
                    "Could not delete image file: "
                            + e.getMessage()
            );
        }

        // Delete database record
        galleryRepository.delete(gallery);
    }
}