package com.stav.server.dal;

import com.stav.server.entities.CompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompaniesDal extends CrudRepository<CompanyEntity, Long> {
    @Query("SELECT id FROM CompanyEntity")
    List<Long> findAllCompaniesIds();

}
