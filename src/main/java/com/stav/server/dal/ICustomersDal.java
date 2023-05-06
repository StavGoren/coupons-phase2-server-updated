package com.stav.server.dal;

import com.stav.server.dto.CustomerDTO;
import com.stav.server.entities.CustomerEntity;
import com.stav.server.exceptions.ServerException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomersDal extends CrudRepository<CustomerEntity, Long> {
    @Query("SELECT NEW com.stav.server.dto.CustomerDTO (cus.fullName, cus.phoneNumber, u.userName, cus.address, comp.id, comp.name) " +
            "FROM CustomerEntity cus JOIN UserEntity u ON cus.user.id = u.id " +
            "JOIN CompanyEntity comp ON u.company.id = comp.id")
    List<CustomerDTO> findAllCustomers();

    @Query("SELECT id FROM CustomerEntity")
    List<CustomerEntity> findAllCustomersIds();

    @Query("SELECT NEW com.stav.server.dto.CustomerDTO (cus.fullName, cus.phoneNumber, u.userName, cus.address, comp.id, comp.name) " +
            "FROM CustomerEntity cus JOIN UserEntity u ON cus.id = cus.user.id " +
            "JOIN CompanyEntity comp ON u.company.id = comp.id " +
            "WHERE id= :customerId")
    CustomerDTO findCustomerById(@Param("customerId") long customerId);



}
