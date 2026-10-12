
package com.serviq;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviq.service.ServiceRecordService;

@RestController
@RequestMapping("/api/service-records")
@CrossOrigin(origins = "http://localhost:5173")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    public ServiceRecordController(
            ServiceRecordService serviceRecordService) {
        this.serviceRecordService = serviceRecordService;
    }

    @PostMapping
    public ResponseEntity<ServiceRecord> addServiceRecord(
            @RequestBody ServiceRecord record) {

        ServiceRecord savedRecord =
                serviceRecordService.addServiceRecord(record);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedRecord);
    }

    @GetMapping
    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordService.getAllServiceRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRecord> getServiceRecordById(
            @PathVariable Long id) {

        ServiceRecord record =
                serviceRecordService.getServiceRecordById(id);

        if (record == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(record);
    }

    @GetMapping("/asset/{assetId}")
    public List<ServiceRecord> getServiceRecordsByAssetId(
            @PathVariable Long assetId) {

        return serviceRecordService
                .getServiceRecordsByAssetId(assetId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecord> updateServiceRecord(
            @PathVariable Long id,
            @RequestBody ServiceRecord updatedRecord) {

        ServiceRecord record =
                serviceRecordService.updateServiceRecord(
                        id, updatedRecord);

        if (record == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(record);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRecord(
            @PathVariable Long id) {

        boolean deleted =
                serviceRecordService.deleteServiceRecord(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
