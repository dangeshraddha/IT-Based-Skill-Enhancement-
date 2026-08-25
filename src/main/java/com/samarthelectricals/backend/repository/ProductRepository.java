package com.samarthelectricals.backend.repository;

import com.samarthelectricals.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}