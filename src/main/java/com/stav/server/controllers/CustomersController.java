package com.stav.server.controllers;

import com.stav.server.beans.Customer;
import com.stav.server.dto.CustomerDTO;
import com.stav.server.entities.CustomerEntity;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.logic.CustomersLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private CustomersLogic customersLogic;


    @Autowired
    public CustomersController(CustomersLogic customersLogic) {
        this.customersLogic = customersLogic;
    }


    @PostMapping
    public void crateCustomer(@RequestBody CustomerEntity customer) throws ServerException {
        System.out.println("Hello");
        customer.getUser().setUserType(UserType.Customer);
        System.out.println(customer);
        this.customersLogic.createCustomer(customer);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
    }

    @GetMapping("{customerId}")
    public CustomerDTO getCustomer(@PathVariable("customerId") int id) throws ServerException{
        CustomerDTO customer =  customersLogic.getCustomerById(id);
        return customer;
    }

//    @GetMapping("/byPage")
//    public List<Customer> getCustomersByPage(@RequestParam("pageNumber") int pageNumber) throws ServerException{
//        List<Customer> customers = customersLogic.getCustomersByPage(pageNumber);
//        return customers;
//    }


    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int id) throws ServerException{
        customersLogic.removeCustomer(id, UserType.Admin);
    }
}
