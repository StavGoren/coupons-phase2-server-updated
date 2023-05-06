package com.stav.server.dto;

import java.util.Date;

public class CouponDTO {
    //    private long couponId;
    private String couponName;
    private double priceInNis;
    private String description;
    private Date startDate;
    private Date endDate;
    private String couponNumber;
    //    private long categoryId;
    private String categoryName;
//    private String companyName;
//    private String companyPhoneNumber;

    public CouponDTO(String couponName, double priceInNis, Date startDate, Date endDate) {
        this.couponName = couponName;
        this.priceInNis = priceInNis;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CouponDTO(String couponName, double priceInNis, String description, Date startDate, Date endDate,
                     String couponNumber, String categoryName) {
        this.couponName = couponName;
        this.priceInNis = priceInNis;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.couponNumber = couponNumber;
        this.categoryName = categoryName;
    }

//    public long getCouponId() {
//        return couponId;
//    }
//
//    public void setCouponId(long couponId) {
//        this.couponId = couponId;
//    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public double getPriceInNis() {
        return priceInNis;
    }

    public void setPriceInNis(double priceInNis) {
        this.priceInNis = priceInNis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }

//    public long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(long categoryId) {
//        this.categoryId = categoryId;
//    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

//    public long getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(long companyId) {
//        this.companyId = companyId;
//    }

//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    public String getCompanyPhoneNumber() {
//        return companyPhoneNumber;
//    }
//
//    public void setCompanyPhoneNumber(String companyPhoneNumber) {
//        this.companyPhoneNumber = companyPhoneNumber;
//    }
//    }


    @Override
    public String toString() {
        return "CouponDTO{" +
                "couponName='" + couponName + '\'' +
                ", priceInNis=" + priceInNis +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", couponNumber='" + couponNumber + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

}
