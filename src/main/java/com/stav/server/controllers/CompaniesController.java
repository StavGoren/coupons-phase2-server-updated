package com.stav.server.controllers;

import com.stav.server.entities.CompanyEntity;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.logic.CompaniesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    private CompaniesLogic companiesLogic;

    @Autowired
    public CompaniesController(CompaniesLogic companiesLogic) {
        this.companiesLogic = companiesLogic;
    }

    @PostMapping
    public void createCompany(@RequestBody CompanyEntity company) throws ServerException {
        companiesLogic.createCompany(company);
    }


    @PutMapping
    public void updateCompany(@RequestBody CompanyEntity company) throws ServerException {
        companiesLogic.updateCompany(company);
    }

    @GetMapping("{companyId}")
    public Optional<CompanyEntity> getCompany(@PathVariable("companyId")int id) throws ServerException{
        return companiesLogic.getCompany(id);
    }

    @GetMapping
    public Iterable<CompanyEntity> getAllCompanies() throws ServerException {
        return companiesLogic.getAllCompanies();
    }

    @DeleteMapping("{companyId}")
    public void deleteCompany(@PathVariable("companyId")int id) throws ServerException{
        companiesLogic.deleteCompany(id, UserType.Admin);
    }
}