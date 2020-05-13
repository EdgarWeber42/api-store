package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.entity.Device;
import com.supinfo.proj.retailr.apistore.data.model.Response;
import com.supinfo.proj.retailr.apistore.data.repository.DeviceRepository;
import com.supinfo.proj.retailr.apistore.data.repository.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.callback.TextInputCallback;
import javax.validation.Valid;

@RestController
public class DeviceController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/devices")
    public ResponseEntity<?> getDevices(){
        logger.info("GET on /devices");
        return ResponseEntity.ok(this.deviceRepository.findAll());
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<?> getDevicesById(@PathVariable Long id){
        logger.info("GET on /devices/{id}");
        if (this.deviceRepository.existsById(id)){
            return ResponseEntity.ok(this.deviceRepository.findById(id).get());
        } else {
            return ResponseEntity.ok(new Response("No devices with id " + id + " found"));
        }
    }

    @PostMapping("/devices/create")
    public ResponseEntity<?> createDevice(@RequestBody @Valid Device device, BindingResult result){
        logger.info("POST on /devices/create");
        if (!result.hasErrors()){
            this.deviceRepository.save(device);
            return ResponseEntity.ok(new Response("device successfully created"));
        } else {
            return ResponseEntity.ok(new Response("invalid JSON format"));
        }
    }

    @PostMapping("/devices/update/{id}")
    public ResponseEntity<?> updateDevice(@RequestBody @Valid Device device, BindingResult result, @PathVariable Long id){
        if (!result.hasErrors()){
            if (this.deviceRepository.existsById(id)){
                if (device.getId() != 0 && device.getId() != id){
                    return ResponseEntity.ok(new Response("conflicting device id between URL and JSON"));
                } else {
                    device.setId(id);
                    this.deviceRepository.save(device);
                    return ResponseEntity.ok(new Response("device successfully updated"));
                }
            }else {
                return ResponseEntity.ok(new Response("device with id : " + id + " does not exist"));
            }
        } else {
            return ResponseEntity.ok(new Response("invalid format"));
        }
    }

    @PostMapping("/devices/delete/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id){
        if (this.deviceRepository.existsById(id)){
            Device device = this.deviceRepository.findById(id).get();
            this.deviceRepository.delete(device);
            return ResponseEntity.ok(new Response("device " + id + " successfully deleted"));
        } else {
            return ResponseEntity.ok(new Response("no device with id : " + id + " found"));
        }
    }
}
