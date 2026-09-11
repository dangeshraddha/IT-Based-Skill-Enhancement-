package com.samarthelectricals.backend.repository;

import com.samarthelectricals.backend.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}