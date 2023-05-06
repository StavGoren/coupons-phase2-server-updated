package com.stav.server.controllers;


import com.stav.server.dto.CouponDTO;
import com.stav.server.entities.CouponEntity;
import com.stav.server.exceptions.ServerException;
import com.stav.server.logic.CouponsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coupons")
public class CouponsController {

    private CouponsLogic couponsLogic;

    @Autowired
    public CouponsController(CouponsLogic couponsLogic) {
        this.couponsLogic = couponsLogic;
    }


    @PostMapping
    public void createCoupon(@RequestBody CouponEntity coupon) throws ServerException {
        couponsLogic.createCoupon(coupon);
    }

    @PutMapping
    public void updateCoupon(@RequestBody CouponEntity coupon) throws ServerException {
        couponsLogic.createCoupon(coupon);
    }

    @GetMapping("{couponId}")
    public Optional<CouponEntity> getCoupon(@PathVariable("couponId") long id) throws ServerException {
        return couponsLogic.getCoupon(id);
    }

    @GetMapping
    public List<CouponDTO> getAllCoupons() throws ServerException {
        return couponsLogic.getAllCoupons();
    }


    @GetMapping("/byDate")
    public List<CouponEntity> getCouponsByMinStartDate(@RequestParam("startDate") Date startDate) throws ServerException {
        return couponsLogic.getCouponsByMinStartDate(startDate);
    }

    @GetMapping("/byCouponName")
    public CouponEntity getCouponByName(@RequestParam("couponName") String name) throws ServerException {
        return couponsLogic.getCouponByName(name);
    }

    @GetMapping("/byCategoryId")
    public List<CouponDTO> getCouponsByCategoryId(@RequestParam("categoryId") long categoryId) throws ServerException {
        return couponsLogic.getCouponsByCategoryId(categoryId);
    }

    @GetMapping("/byCategoryName")
    public List<CouponDTO> getCouponByCategoryName(@RequestParam("categoryName") String categoryName) throws ServerException {
        return couponsLogic.getCouponsByCategoryName(categoryName);
    }

    @DeleteMapping("{couponId}")
    public void deleteCoupon(@PathVariable("couponId") long id) throws ServerException {
        couponsLogic.deleteCoupon(id);
    }


}
