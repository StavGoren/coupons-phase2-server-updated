package com.stav.server.entities;
import com.stav.server.enums.UserType;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "PASSWORD", unique = false, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE", nullable = false)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private CompanyEntity company;

    public UserEntity(){

    }

    public UserEntity(long id, String userName, String password, UserType userType, CompanyEntity company) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
