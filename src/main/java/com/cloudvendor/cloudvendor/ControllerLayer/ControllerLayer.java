package com.cloudvendor.cloudvendor.ControllerLayer;

import com.cloudvendor.cloudvendor.CloudVendorDTO.VendorRequestDTO;
import com.cloudvendor.cloudvendor.CloudVendorDTO.VendorResponseDTO;
import com.cloudvendor.cloudvendor.ServiceLayer.ServiceLayer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Vendor")
public class ControllerLayer {
    //1>Connect Controller layer to the Service Layer via Construtor injection
 private final ServiceLayer serviceLayer;

    public ControllerLayer(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    //2>Create method() to test
    //2.1> getAllUser
    @GetMapping("/getAllVendor")
    public ResponseEntity<List<VendorResponseDTO>>getAllVendor(){
        List<VendorResponseDTO> getAllUser=serviceLayer.getAllVendor();
        return ResponseEntity.ok(getAllUser);
    }

    //2.2>getSingleUser
    @GetMapping("/getSingleVendor/{id}")
    public ResponseEntity<VendorResponseDTO>getSingleVendor(@PathVariable int id){
        VendorResponseDTO getSingleUser=serviceLayer.getSingleVendor(id);
        return ResponseEntity.ok(getSingleUser);
    }

    //2.3>addUser
    @PostMapping("/addVendor")
    public ResponseEntity<VendorResponseDTO>addVendor(@Valid @RequestBody VendorRequestDTO userRequestDTO){
        VendorResponseDTO addUser=serviceLayer.addVendor(userRequestDTO);
       return new ResponseEntity<>(addUser,HttpStatus.CREATED);
    }

    //2.4>updateUser
    @PutMapping("/updateVendor/{id}")
    public ResponseEntity<VendorResponseDTO>updateVendor(@Valid @RequestBody VendorRequestDTO userRequestDTO, @PathVariable int id ){
        VendorResponseDTO updateUser=serviceLayer.updateVendor(userRequestDTO,id);
     return ResponseEntity.ok(updateUser);
    }

    //2.5>deleteUser
    @DeleteMapping("/deleteVendor/{id}")
  public ResponseEntity<String>deleteVendor(@PathVariable int id){
          serviceLayer.deleteVendor(id);
         return ResponseEntity.ok("Vendor details deleted");
    }

}

