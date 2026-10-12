
package com.serviq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.serviq.ServiceRecord;
import com.serviq.repository.ServiceRecordRepository;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;

    public ServiceRecordService(
            ServiceRecordRepository serviceRecordRepository) {
        this.serviceRecordRepository = serviceRecordRepository;
    }

    public ServiceRecord addServiceRecord(ServiceRecord record) {
        return serviceRecordRepository.save(record);
    }

    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }

    public ServiceRecord getServiceRecordById(Long id) {
        return serviceRecordRepository.findById(id).orElse(null);
    }

    public List<ServiceRecord> getServiceRecordsByAssetId(Long assetId) {
        return serviceRecordRepository.findAll()
                .stream()
                .filter(record -> assetId.equals(record.getAssetId()))
                .toList();
    }

    public ServiceRecord updateServiceRecord(
            Long id, ServiceRecord updatedRecord) {

        ServiceRecord existingRecord =
                serviceRecordRepository.findById(id).orElse(null);

        if (existingRecord == null) {
            return null;
        }

        existingRecord.setAssetId(updatedRecord.getAssetId());
        existingRecord.setServiceDate(updatedRecord.getServiceDate());
        existingRecord.setServiceType(updatedRecord.getServiceType());
        existingRecord.setDescription(updatedRecord.getDescription());
        existingRecord.setServiceProvider(updatedRecord.getServiceProvider());
        existingRecord.setCost(updatedRecord.getCost());

        return serviceRecordRepository.save(existingRecord);
    }

    public boolean deleteServiceRecord(Long id) {
        if (!serviceRecordRepository.existsById(id)) {
            return false;
        }

        serviceRecordRepository.deleteById(id);
        return true;
    }
}
