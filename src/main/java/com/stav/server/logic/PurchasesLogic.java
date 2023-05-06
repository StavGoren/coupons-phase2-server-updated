package com.stav.server.logic;

import com.stav.server.dal.IPurchasesDal;
import com.stav.server.dto.PurchaseCustomerDto;
import com.stav.server.entities.Purchase;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.ValidationsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchasesLogic {
    private IPurchasesDal iPurchasesDal;

    @Autowired
    public PurchasesLogic(IPurchasesDal iPurchasesDal) {
        this.iPurchasesDal = iPurchasesDal;
    }

    /**********************
     * Public methods
     **********************/

    public long createPurchase(Purchase purchase) throws ServerException {
        ValidationsUtils.validateNumber(purchase.getAmount());
        iPurchasesDal.save(purchase);
        return purchase.getId();
    }

    public void removePurchaseById(long id, UserType userType) throws ServerException {
        isIdExist(id);
        ValidationsUtils.validateUserType(userType);
        iPurchasesDal.deleteById(id);
    }


    public Optional<Purchase> getPurchase(long id) throws ServerException {
        isIdExist(id);
        return iPurchasesDal.findById(id);
    }


//    public List<PurchaseCustomerDto> getPurchasesAndCustomersByPage(int pageNumber) throws ServerException {
//        ValidationsUtils.validateNumber(pageNumber);
//        return iPurchasesDal.findPurchasesAndCustomersByPage(pageNumber);
//    }

    /*********************
     * Validation
     **********************/


    private boolean isIdExist(long id) throws ServerException{
        List<Long> purchaseIdList = iPurchasesDal.findAllPurchaseIds();
        for (Long purchaseId : purchaseIdList) {
            if(purchaseId == id) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " no purchase with ID " + id + " found");
    }
}
