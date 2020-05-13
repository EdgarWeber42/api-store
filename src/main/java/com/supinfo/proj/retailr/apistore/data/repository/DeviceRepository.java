package com.supinfo.proj.retailr.apistore.data.repository;

import com.supinfo.proj.retailr.apistore.data.entity.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    boolean existsById(Long id);
    Optional<Device> findById(Long id);
}
