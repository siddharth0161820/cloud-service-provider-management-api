package com.cloudvendor.cloudvendor.ServiceLayer;

import com.cloudvendor.cloudvendor.CloudVendorDTO.VendorRequestDTO;
import com.cloudvendor.cloudvendor.CloudVendorDTO.VendorResponseDTO;
import com.cloudvendor.cloudvendor.ModelEntity.Vendor;
import com.cloudvendor.cloudvendor.RepositoryLayer.RepositoryLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//Enables Mockito support in JUnit 5
@ExtendWith(MockitoExtension.class)
public class CloudVendorTest {

    //Creates a mock object for the Repository layer (No DB calls)
    @Mock
    RepositoryLayer repositoryLayer;

    // Creates a real object of ServiceLayerImplementation
    // and injects all mocks automatically
    @InjectMocks
    ServiceLayerImplementation serviceLayerImplementation;

    // Test Case for addVendor()
    @Test
    public void addVendorShouldAddVendorSuccessfully() {
        // Arrange (Given) – Setup input & mock behavior

        // 1. Create DTO input that simulates a client request
        VendorRequestDTO vendorRequestDTO = new VendorRequestDTO("Sidharth", "sid@test.com");

        // 2. Prepare the Vendor entity that the service will send to repository.save()
        Vendor vendorEntity = new Vendor();
        vendorEntity.setName(vendorRequestDTO.getName());
        vendorEntity.setEmail(vendorRequestDTO.getEmail());

        // 3. Mock the saved Vendor object that repository.save() should return
        Vendor savedVendor = new Vendor();
        savedVendor.setId(108);
        savedVendor.setName("ShivShakti");
        savedVendor.setEmail("shivshakti@gmail.com");

        // 4. Mock repository behavior: when save() is called, return savedVendor
        Mockito.when(repositoryLayer.save(Mockito.any(Vendor.class))).thenReturn(savedVendor);

        // Act (When) – Call the actual service method
        VendorResponseDTO response = serviceLayerImplementation.addVendor(vendorRequestDTO);

        // Assert (Then) – Validate the returned data
        Assertions.assertEquals("ShivShakti", response.getName());
        Assertions.assertEquals("shivshakti@gmail.com", response.getEmail());
        Assertions.assertEquals(108, response.getId());
    }


    //  Test Case 2: getSingleVendor() – Positive Flow
    @Test
    public void getSingleVendorShouldReturnVendor_WhenVendorExists() {
        // Arrange
        int vendorId = 108;

        Vendor existingVendor = new Vendor();
        existingVendor.setId(108);
        existingVendor.setName("Mahadev");
        existingVendor.setEmail("mahadev@gmail.com");

        Mockito.when(repositoryLayer.findById(vendorId))
                .thenReturn(java.util.Optional.of(existingVendor));

        // Act
        VendorResponseDTO response = serviceLayerImplementation.getSingleVendor(vendorId);

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals(108, response.getId());
        Assertions.assertEquals("Mahadev", response.getName());
        Assertions.assertEquals("mahadev@gmail.com", response.getEmail());
    }

    //  Test Case 3: getSingleVendor() – Negative Flow
    @Test
    public void getSingleVendorShouldThrowException_WhenVendorNotFound() {
        // Arrange
        int vendorId = 999;
        Mockito.when(repositoryLayer.findById(vendorId))
                .thenReturn(java.util.Optional.empty());

        // Act & Assert
        Assertions.assertThrows(
                com.cloudvendor.cloudvendor.GlobalExceptionHandling.NOTFOUNDEXCEPTION.class,
                () -> serviceLayerImplementation.getSingleVendor(vendorId)
        );
    }

}
