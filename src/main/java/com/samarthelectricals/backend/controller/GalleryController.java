package com.samarthelectricals.backend.controller;

import com.samarthelectricals.backend.entity.Gallery;
import com.samarthelectricals.backend.service.GalleryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin(origins = "*")
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    // GET all gallery images
    @GetMapping
    public ResponseEntity<List<Gallery>> getAllImages() {

        return ResponseEntity.ok(
                galleryService.getAllImages()
        );
    }

    // GET image by ID
    @GetMapping("/{id}")
    public ResponseEntity<Gallery> getImageById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                galleryService.getImageById(id)
        );
    }

    // Upload image
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Gallery> uploadImage(

            @RequestParam("file")
            MultipartFile file,

            @RequestParam(value = "caption", required = false)
            String caption,

            @RequestParam(value = "description", required = false)
            String description) {

        Gallery gallery =
                galleryService.uploadImage(
                        file,
                        caption,
                        description
                );

        return ResponseEntity.ok(gallery);
    }

    // Delete image
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(
            @PathVariable Long id) {

        galleryService.deleteImage(id);

        return ResponseEntity.ok(
                "Gallery image deleted successfully"
        );
    }
}