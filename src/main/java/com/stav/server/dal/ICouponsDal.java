package com.stav.server.dal;

import com.stav.server.dto.CouponDTO;
import com.stav.server.entities.CouponEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ICouponsDal extends CrudRepository<CouponEntity, Long> {

    @Query("SELECT NEW com.stav.server.dto.CouponDTO (coup.name, coup.priceInNis, coup.startDate, coup.endDate) " +
            "FROM CouponEntity coup")
    List<CouponDTO> findAllCoupons();

    @Query("SELECT name, priceInNis, startDate, endDate FROM CouponEntity WHERE startDate= :startDate")
    List<CouponEntity> findCouponsByMinStartDate(@Param("startDate") Date startDate);

    @Query("SELECT coup.name, coup.priceInNis, coup.startDate, coup.endDate FROM CouponEntity coup WHERE name= :name")
    CouponEntity findCouponByName(@Param("name") String name);

    @Query("SELECT new com.stav.server.dto.CouponDTO (coup.name, coup.priceInNis, coup.description, " +
            "coup.startDate, coup.endDate, coup.couponNumber, cat.name) FROM " +
            "CouponEntity coup JOIN CategoryEntity cat ON coup.category.id = cat.id " +
            "WHERE cat.id= :categoryId")
    List<CouponDTO> findCouponsByCategoryId(@Param("categoryId") long categoryId);

    @Query("SELECT new com.stav.server.dto.CouponDTO (coup.name, coup.priceInNis, coup.description, " +
            "coup.startDate, coup.endDate, coup.couponNumber, cat.name) FROM " +
            "CouponEntity coup JOIN CategoryEntity cat ON coup.category.id = cat.id " +
            "WHERE cat.name= :categoryName")
    List<CouponDTO> findCouponsByCategoryName(@Param("categoryName") String categoryName);


    @Query("SELECT id FROM CouponEntity")
    List<Long> findAllCouponsIds();

    @Query("Select name FROM CouponEntity")
    List<String> findAllCouponsNames();

    //CategoryEntity cat ON coup.category.id = cat.id " +
    //            " WHERE coup.category.id= :categoryId"
}
