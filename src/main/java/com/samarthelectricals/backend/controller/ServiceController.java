package com.samarthelectricals.backend.controller;

import com.samarthelectricals.backend.entity.Service;
import com.samarthelectricals.backend.service.ServiceService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // GET all services
    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    // GET service by ID
    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    // ADD service
    @PostMapping
    public Service addService(@RequestBody Service service) {
        return serviceService.addService(service);
    }

    // UPDATE service
    @PutMapping("/{id}")
    public Service updateService(
            @PathVariable Long id,
            @RequestBody Service service) {

        return serviceService.updateService(id, service);
    }

    // DELETE service
    @DeleteMapping("/{id}")
    public String deleteService(@PathVariable Long id) {

        serviceService.deleteService(id);

        return "Service deleted successfully";
    }
}