
package com.serviq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.serviq.Asset;
import com.serviq.repository.AssetRepository;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Asset addAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Asset getAssetById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }

    public Asset updateAsset(Long id, Asset updatedAsset) {
        Asset existingAsset = assetRepository.findById(id).orElse(null);

        if (existingAsset == null) {
            return null;
        }

        existingAsset.setAssetName(updatedAsset.getAssetName());
        existingAsset.setCategory(updatedAsset.getCategory());
        existingAsset.setBrand(updatedAsset.getBrand());
        existingAsset.setModel(updatedAsset.getModel());
        existingAsset.setPurchaseDate(updatedAsset.getPurchaseDate());
        existingAsset.setWarrantyExpiry(updatedAsset.getWarrantyExpiry());
        existingAsset.setDescription(updatedAsset.getDescription());

        return assetRepository.save(existingAsset);
    }

    public boolean deleteAsset(Long id) {
        if (!assetRepository.existsById(id)) {
            return false;
        }

        assetRepository.deleteById(id);
        return true;
    }
}
