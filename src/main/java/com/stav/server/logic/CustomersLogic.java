package com.stav.server.logic;

import com.stav.server.beans.Customer;
import com.stav.server.dal.ICustomersDal;
import com.stav.server.dto.CustomerDTO;
import com.stav.server.entities.CustomerEntity;
import com.stav.server.entities.UserEntity;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.ValidationsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersLogic {
    private ICustomersDal iCustomersDal;
    private UsersLogic usersLogic;

    @Autowired
    public CustomersLogic(ICustomersDal iCustomersDal, UsersLogic usersLogic) {
        this.iCustomersDal = iCustomersDal;
        this.usersLogic = usersLogic;
    }

    public long createCustomer(CustomerEntity customer) throws ServerException {
        validateCustomer(customer);
        iCustomersDal.save(customer);
        return customer.getId();
    }

    public void updateCustomer(CustomerEntity customer) throws ServerException {
        isIdExist(customer.getId());
        validateCustomer(customer);
        iCustomersDal.save(customer);
    }

//    public void updateCustomerFullName(CustomerEntity customer) throws ServerException {
//        ValidationsUtils.validateNameLength(customer.getFullName());
//        isIdExist(customer.getId());
//        iCustomersDal.save(customer);
//    }
//
//    public void updatePhoneNumber(String phoneNumber, int id) throws ServerException {
//        ValidationsUtils.validatePhoneNumber(phoneNumber);
//        isValidPhoneNumber(phoneNumber);
//        isIdExist(id);
//        customersDal.updateCustomerPhoneNumber(phoneNumber, id);
//    }
//
//    public void updateAddress(String address, int id) throws ServerException {
//        ValidationsUtils.validateAddress(address);
//        isIdExist(id);
//        customersDal.updateCustomerAddress(address, id);
//    }

    public void removeCustomer(long id, UserType userType) throws ServerException {
        isIdExist(id);
        ValidationsUtils.validateUserType(userType);
        usersLogic.deleteUser(id, userType);
        iCustomersDal.deleteById(id);
    }

    public CustomerDTO getCustomerById(long id) throws ServerException {
        isIdExist(id);
        return iCustomersDal.findCustomerById(id);
    }

//    public List<CustomerDTO> getCustomersByPage(int pageNumber) throws ServerException {
//        ValidationsUtils.validateNumber(pageNumber);
//        return iCustomersDal.getAllCustomersByPage(pageNumber);
//    }


    /*********************
     * ValidationsUtils
     *********************/


    private boolean isIdExist(long id) throws ServerException {
        List<CustomerEntity> customersIdList = iCustomersDal.findAllCustomersIds();
        for (int i = 0; i < customersIdList.size(); i++) {
            if (customersIdList.get(i).getId() == id) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " please make sure you entered the correct customer ID");
    }


    private void validateCustomer(CustomerEntity customer) throws ServerException {
//        UserEntity user = customer.getUser();
//        usersLogic.validateUser(user);
        ValidationsUtils.validateNameLength(customer.getFullName());
        isValidPhoneNumber(customer.getPhoneNumber());
        ValidationsUtils.validateAddress(customer.getAddress());
    }


    // Check if phone number already exists
    private boolean isValidPhoneNumber(String phoneNumber) throws ServerException {
        ValidationsUtils.validatePhoneNumber(phoneNumber);
        List<CustomerDTO> customerList = iCustomersDal.findAllCustomers();
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getPhoneNumber().equals(phoneNumber)) {
                throw new ServerException(ErrorType.PHONE_NUMBER_EXISTS, " please make sure you entered the right number");
            }
        }
        return true;
    }
}
