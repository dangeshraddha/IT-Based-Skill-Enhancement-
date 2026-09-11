package com.samarthelectricals.backend.service;

import com.samarthelectricals.backend.entity.Service;
import com.samarthelectricals.backend.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    public Service updateService(Long id, Service service) {

        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        existingService.setTitle(service.getTitle());
        existingService.setDescription(service.getDescription());
        existingService.setIcon(service.getIcon());
        existingService.setActive(service.isActive());

        return serviceRepository.save(existingService);
    }

    public void deleteService(Long id) {

        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Service not found");
        }

        serviceRepository.deleteById(id);
    }
}