package com.samarthelectricals.backend.repository;

import com.samarthelectricals.backend.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}