package com.stav.server.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue
    private long id;

    //    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private UserEntity user;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Purchase> purchases;


//    @ManyToMany

    public CustomerEntity() {

    }

    public CustomerEntity(UserEntity user, String fullName, String phoneNumber, String address) {
        this.user = user;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        return "CustomerEntity{" +
                "id=" + id +
                ", user=" + user +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
