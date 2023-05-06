package com.stav.server.dal;

import com.stav.server.entities.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPurchasesDal extends CrudRepository<Purchase, Long> {
    @Query("SELECT id FROM Purchase")
    List<Long> findAllPurchaseIds();
}
