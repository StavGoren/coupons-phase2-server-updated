package com.stav.server.logic;

import com.stav.server.dal.ICompaniesDal;
import com.stav.server.entities.CompanyEntity;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.ValidationsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.xml.catalog.CatalogResolver;
import java.util.List;
import java.util.Optional;

@Service
public class CompaniesLogic {

    private ICompaniesDal iCompaniesDal;

    @Autowired
    public CompaniesLogic(ICompaniesDal iCompaniesDal) {
        this.iCompaniesDal = iCompaniesDal;
    }


    /*******************************
     Public methods and actions
     *******************************/

    public long createCompany(CompanyEntity company) throws ServerException {
        validateCompany(company);
        iCompaniesDal.save(company);
        return company.getId();
    }

    public void updateCompany(CompanyEntity company) throws ServerException {
        validateCompany(company);
        iCompaniesDal.save(company);
    }

    public Optional<CompanyEntity> getCompany(long id) throws ServerException {
        isValidId(id);
        return iCompaniesDal.findById(id);
    }

    public Iterable<CompanyEntity> getAllCompanies() throws ServerException {
        return iCompaniesDal.findAll();
    }

    public void deleteCompany(int id, UserType userType) throws ServerException {
//        isValidId(id);
        ValidationsUtils.validateUserType(userType);
        iCompaniesDal.deleteById((long) id);
    }


    /********************
     ValidationsUtils
     *********************/

    protected boolean isValidId(long id) throws ServerException {
//        ValidationsUtils.validateNumber((int)id);

        // Checking if ID exists
        List<Long> companiesIdList = iCompaniesDal.findAllCompaniesIds();
        for (long companyId : companiesIdList) {
            if(companyId == id) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " failed to find a company with the given ID");
    }

    private void validateCompany(CompanyEntity company) throws ServerException {
        validateCompanyName(company.getName());
        validatePhoneNumber(company.getPhoneNumber());
        ValidationsUtils.validateAddress(company.getAddress());
    }


    private void validatePhoneNumber(String phoneNumber) throws ServerException {
        ValidationsUtils.validatePhoneNumber(phoneNumber);

        // Checking if phone number already exists
//        List<CompanyEntity> companyList = companiesDal.getAllCompanies();
//        for (int i = 0; i < companyList.size(); i++) {
//            if (companyList.get(i).getPhoneNumber().equals(phoneNumber)) {
//                throw new ServerException(ErrorType.PHONE_NUMBER_EXISTS, " please make sure the data is valid");
//            }
//        }
    }

    private void validateCompanyName(String name) throws ServerException {
        ValidationsUtils.validateNameLength(name);

        // Checking if name already exists
//        List<CompanyEntity> companyList = companiesDal.getAllCompanies();
//        for (int i = 0; i < companyList.size(); i++) {
//            if (companyList.get(i).getName().equals(name)) {
//                throw new ServerException(ErrorType.NAME_EXISTS, " please find another name");
//            }
//        }
    }
}