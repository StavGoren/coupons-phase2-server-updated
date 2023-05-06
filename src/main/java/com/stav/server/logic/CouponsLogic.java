package com.stav.server.logic;


import com.stav.server.dal.ICouponsDal;
import com.stav.server.dto.CouponDTO;
import com.stav.server.entities.CouponEntity;
import com.stav.server.enums.ErrorType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.ValidationsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CouponsLogic {
    private ICouponsDal iCouponsDal;
    private CategoriesLogic categoriesLogic;

    @Autowired
    public CouponsLogic(ICouponsDal iCouponsDal, CategoriesLogic categoriesLogic) {
        this.iCouponsDal = iCouponsDal;
        this.categoriesLogic = categoriesLogic;
    }

    /*******************************
     Public methods and actions
     ******************************/


    public long createCoupon(CouponEntity coupon) throws ServerException {
        validateCoupon(coupon);
        iCouponsDal.save(coupon);
        return coupon.getId();
    }

    public void updateCoupon(CouponEntity coupon) throws ServerException {
        validateCoupon(coupon);
        iCouponsDal.save(coupon);
    }


    public Optional<CouponEntity> getCoupon(long id) throws ServerException {
        isValidId(id);
        return iCouponsDal.findById(id);
    }

    public List<CouponDTO> getAllCoupons() throws ServerException {
        return iCouponsDal.findAllCoupons();
    }

    public CouponEntity getCouponByName(String name) throws ServerException {
        isCouponNameExist(name);
        return iCouponsDal.findCouponByName(name);
    }

    public List<CouponEntity> getCouponsByMinStartDate(Date startDate) throws ServerException {
        // Add date validation
        return iCouponsDal.findCouponsByMinStartDate(startDate);
    }

    public List<CouponDTO> getCouponsByCategoryId(long categoryId) throws ServerException {
        return iCouponsDal.findCouponsByCategoryId(categoryId);
    }

    public List<CouponDTO> getCouponsByCategoryName(String categoryName) throws ServerException {
        categoriesLogic.isNameExist(categoryName);
        return iCouponsDal.findCouponsByCategoryName(categoryName);
    }

    public void deleteCoupon(long id) throws ServerException {
        isValidId(id);
        iCouponsDal.deleteById(id);
    }


    /*********************************
     * Private methods (validations)
     *********************************/

    private void validateCoupon(CouponEntity coupon) throws ServerException {
        validateCouponName(coupon.getName());
        validatePriceValue(coupon.getPriceInNis());
        validateDescriptionLength(coupon.getDescription());
        validateCouponNumberCharacters(coupon.getCouponNumber());

    }

    private boolean isValidId(long id) throws ServerException {
//        ValidationsUtils.validateNumber(id);

        List<Long> couponIdList = iCouponsDal.findAllCouponsIds();
        for (long couponId : couponIdList) {
            if (couponId == id) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " please make sure you entered a correct id");
    }

    private void validateCouponNumberCharacters(String couponNumber) throws ServerException {
        for (int i = 0; i < couponNumber.length(); i++) {
            if(!Character.isLetterOrDigit(couponNumber.charAt(i))) {
                throw new ServerException(ErrorType.INVALID_COUPON_CHARACTERS, " please check you entered valid characters");
            }
        }
    }

    private void validateDescriptionLength(String description) throws ServerException {
        if(description.length() < 10){
            throw new ServerException(ErrorType.INVALID_DESCRIPTION_LENGTH, " please make sure there is enough information");
        }
        if(description.length() > 250){
            throw new ServerException(ErrorType.INVALID_DESCRIPTION_LENGTH, " please keep the information elaborated and concise");
        }
    }

    private void validatePriceValue(Double price) throws ServerException {
        if(price < 1) {
            throw new ServerException(ErrorType.INVALID_PRICE, " please make sure the value is 1 NIS or greater");
        }
    }

    private void validateCouponName(String name) throws ServerException {
        ValidationsUtils.validateNameLength(name);
    }


    private boolean isCouponNameExist(String name) throws ServerException {
        List<String> names = iCouponsDal.findAllCouponsNames();

        for(String couponName : names) {
            if(name == couponName) {
                return true;
            }
        }
        throw new ServerException(ErrorType.NAME_NOT_EXIST, " make sure you entered the right name");
    }


//    private boolean isCouponNameExist(String name) throws ServerException {
//        boolean isNameExist = ValidationsUtils.isDataExist((InterfaceType) iCouponsDal.findAllCouponsNames(), name);
//        if(!isNameExist) {
//            throw new ServerException(ErrorType.NAME_NOT_EXIST, " make sure you entered the right name");
//        }
//        return true;
//    }
}