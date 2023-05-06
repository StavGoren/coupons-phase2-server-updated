package com.stav.server.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

//    @ManyToOne
//    @JoinColumn(name = "CATEGORY_ID")
//    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "company", fetch = FetchType.LAZY)
    private List<CouponEntity> coupons;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "company", fetch = FetchType.LAZY)
    private List<UserEntity> users;


    public CompanyEntity() {

    }

    public CompanyEntity(long id, String name, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", coupons=" + coupons +
                ", users=" + users +
                '}';
    }
}
