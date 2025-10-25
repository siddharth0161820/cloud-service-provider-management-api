package com.cloudvendor.cloudvendor.CloudVendorDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class VendorRequestDTO {
    //1>Attributes
    @NotEmpty
    @Size(max=51,message = "The name should be valid and not empty")
    private String name;

    @NotEmpty
    @Email(message = "The email id should be appropriate")
    private String email;


    //2>Default Parameter
    public VendorRequestDTO() {
    }

    //3>Parameterized constructor

    public VendorRequestDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
    //5>Getter & Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //6>toString()

    @Override
    public String toString() {
        return "CloudVendorRequestDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
