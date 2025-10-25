package com.cloudvendor.cloudvendor.ServiceLayer;

import com.cloudvendor.cloudvendor.CloudVendorDTO.VendorRequestDTO;
import com.cloudvendor.cloudvendor.CloudVendorDTO.VendorResponseDTO;
import com.cloudvendor.cloudvendor.GlobalExceptionHandling.NOTFOUNDEXCEPTION;
import com.cloudvendor.cloudvendor.ModelEntity.Vendor;
import com.cloudvendor.cloudvendor.RepositoryLayer.RepositoryLayer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLayerImplementation implements ServiceLayer{
    //1> Connect Service Layer to the Repository Layer via Constructor injection
    private final RepositoryLayer repositoryLayer;

    public ServiceLayerImplementation(RepositoryLayer repositoryLayer) {
        this.repositoryLayer = repositoryLayer;
    }

    @Override
    public List<VendorResponseDTO> getAllVendor() {
        return repositoryLayer.findAll()
                .stream()
                .map(allVendor->new VendorResponseDTO(
                        allVendor.getId(),
                        allVendor.getName(),
                        allVendor.getEmail(),
                        allVendor.getCreatedAt(),
                        allVendor.getUpdatedAt()
                )).toList();
    }

    @Override
    public VendorResponseDTO getSingleVendor(int id) {
        return repositoryLayer.findById(id)
                .map(singleVendor->new VendorResponseDTO(
                        singleVendor.getId(),
                        singleVendor.getName(),
                        singleVendor.getEmail(),
                        singleVendor.getCreatedAt(),
                        singleVendor.getUpdatedAt()
                )).orElseThrow(()->new NOTFOUNDEXCEPTION("Vendor Details not found by Id: "+id));
    }

    @Override
    public VendorResponseDTO addVendor(VendorRequestDTO vendorRequestDTO) {
        Vendor addVendor = new Vendor();
        //1>Convert dto to entity
        addVendor.setName(vendorRequestDTO.getName());
        addVendor.setEmail(vendorRequestDTO.getEmail());

        //2>save to database
        Vendor saveVendor = repositoryLayer.save(addVendor);
        //3>Return details of users
          return new VendorResponseDTO(
                  saveVendor.getId(),
                  saveVendor.getName(),
                  saveVendor.getEmail(),
                  saveVendor.getCreatedAt(),
                  saveVendor.getUpdatedAt()
          );
    }

    @Override
    public VendorResponseDTO updateVendor(VendorRequestDTO vendorRequestDTO, int id) {
        //1>Check of the user details exists to be updated in DTO exists in the Database or not to avoid errors
        Vendor exisitngVendor=repositoryLayer.findById(id).orElseThrow(()->new NOTFOUNDEXCEPTION("Vendor details not found by id that is "+id));

        //2>If data found then
        //update DTO
        exisitngVendor.setName(vendorRequestDTO.getName());
        exisitngVendor.setEmail(vendorRequestDTO.getEmail());

        //2>Save to the Repository
        Vendor exsistngVendor=repositoryLayer.save(exisitngVendor);
        //3>return new response

        return new VendorResponseDTO(
                exsistngVendor.getId(),
                exsistngVendor.getName(),
                exsistngVendor.getEmail(),
                exsistngVendor.getCreatedAt(),
                exsistngVendor.getUpdatedAt()
        );
    }

    @Override
    public void deleteVendor(int id) {
        //check if users to be deleted exists in the DTO or not
       Vendor existngVendor=repositoryLayer.findById(id).orElseThrow(()->new NOTFOUNDEXCEPTION("Please enter the valid Vendor details to delete"));
       //if user details found
        repositoryLayer.deleteById(id);

    }
}
