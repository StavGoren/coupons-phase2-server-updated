package com.stav.server.entities;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;
//
//    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "category", fetch = FetchType.EAGER)
//    private List<CompanyEntity> companies;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "category", fetch = FetchType.LAZY)
    private List<CouponEntity> couponEntities;

//    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//    private List<PurchaseEntity> purchasesList;

    public CategoryEntity() {

    }

    public CategoryEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<CouponEntity> getCouponEntities() {
        return couponEntities;
    }

    public void setCouponEntities(List<CouponEntity> couponEntities) {
        this.couponEntities = couponEntities;
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

}
