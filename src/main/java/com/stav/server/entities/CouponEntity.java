package com.stav.server.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "coupons")
public class CouponEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "PRICE_IN_NIS", nullable = false)
    private double priceInNis;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "COUPON_NUMBER", unique = true, nullable = false)
    private String couponNumber;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private CompanyEntity company;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Purchase> purchasesList;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<CustomerEntity> customersList;


    public CouponEntity(){
    }

    public CouponEntity(long id, String name, double priceInNis, String description, Date startDate, Date endDate,
                        CategoryEntity category, CompanyEntity company, String couponNumber) {
        this.id = id;
        this.name = name;
        this.priceInNis = priceInNis;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.company = company;
        this.couponNumber = couponNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }

}
