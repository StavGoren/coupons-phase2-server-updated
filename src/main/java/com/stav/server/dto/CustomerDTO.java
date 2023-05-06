package com.stav.server.dto;

public class CustomerDTO {
    private String fullName;
    private String phoneNumber;
    private String userName;
    private String address;
    private long companyId;
    private String companyName;

    public CustomerDTO(String fullName, String phoneNumber, String userName, String address, long companyId, String companyName) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.address = address;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}
