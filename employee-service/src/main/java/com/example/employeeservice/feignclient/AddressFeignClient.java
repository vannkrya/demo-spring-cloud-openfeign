package com.example.employeeservice.feignclient;

import com.example.employeeservice.model.response.AddressResponse;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//required name and url this using without load balancer
@FeignClient(
        name = "address-service",
        url = "http://localhost:8081/address-service"
)

//with load balancer
//@FeignClient(
//        name = "address-service",
//        path = "/address-service"
//)
//@RibbonClient(name = "address-service")
public interface AddressFeignClient {

    @GetMapping("/addresses/{id}")
    AddressResponse getAddressByEmployeeId(@PathVariable Long id);

}
