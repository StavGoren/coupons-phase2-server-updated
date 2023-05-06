package com.stav.server.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "AMOUNT", nullable = false)
    private int amount;

    @Column(name = "DATE", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "COUPON_ID")
    private CouponEntity coupon;

//    @ManyToOne
//    @JoinColumn(name = "CATEGORY_ID")
//    private CategoryEntity category;

    public Purchase() {

    }

    public Purchase(long id, int amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}