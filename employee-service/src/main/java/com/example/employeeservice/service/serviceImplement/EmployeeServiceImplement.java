package com.example.employeeservice.service.serviceImplement;

import com.example.employeeservice.feignclient.AddressFeignClient;
import com.example.employeeservice.model.entity.Employee;
import com.example.employeeservice.model.response.AddressResponse;
import com.example.employeeservice.model.response.EmployeeResponse;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImplement implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
//    private final WebClient webClient;

    private final AddressFeignClient addressFeignClient;

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

        //using webclient
        //make Microservices Communication using WebClient
//        AddressResponse addressResponse = webClient
//                .get()
//                .uri("/addresses/" + id)
//                .retrieve().bodyToMono(AddressResponse.class)
//                .block();

        //using spring cloud openFeign for make microservice communication
        AddressResponse addressResponse = addressFeignClient.getAddressByEmployeeId(id);

        System.out.println("addressResponse: " + addressResponse);

        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }



}
