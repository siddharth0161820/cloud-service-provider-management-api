package com.cloudvendor.cloudvendor.ServiceLayer;

import com.cloudvendor.cloudvendor.CloudVendorDTO.VendorRequestDTO;
import com.cloudvendor.cloudvendor.CloudVendorDTO.VendorResponseDTO;

import java.util.List;

public interface ServiceLayer {
    public List<VendorResponseDTO> getAllVendor();
    public VendorResponseDTO getSingleVendor(int id);
    public VendorResponseDTO addVendor(VendorRequestDTO vendorRequestDTO);
    public VendorResponseDTO updateVendor(VendorRequestDTO vendorRequestDTO, int id );
    public void deleteVendor(int id);
}
