package com.stav.server.dal;

import com.stav.server.dto.UserDTO;
import com.stav.server.entities.UserEntity;
import com.stav.server.enums.UserType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface IUsersDal extends CrudRepository<UserEntity, Long>{

    @Query("SELECT u.userName, u.password, u.userType FROM UserEntity u where u.id= :userId")
    UserEntity findUser(@Param("userId") long userId);


    @Query("SELECT new com.stav.server.dto.UserDTO (u.userName, u.userType, comp.name) " +
            "FROM UserEntity u JOIN CompanyEntity comp on  u.company.id = comp.id " +
            "WHERE comp.id= :companyId")
    List<UserDTO> findUsersByCompanyId(@Param("companyId") long companyId);


//    @Query("SELECT u.userName, u.password, u.userType FROM UserEntity u WHERE u.userType= :userType")
//    List<UserDTO> findUsersByUserType(@Param("userType") UserType userType);
//
//    @Query("SELECT u.userName, u.password, u.userType FROM UserEntity u")
//    List<UserDTO> findAllUsersNoIds();
//
//    @Query("SELECT u.id, u.userName, u.password, u.userType FROM UserEntity u")
//    List<UserEntity> findAllUsersIncludingIds();
//
//    @Query("SELECT new com.stav.server.dto.UserDTO (u.userName, u.userType, c.name) " +
//            "FROM UserEntity u JOIN CompanyEntity c on u.company.id = c.id WHERE u.company.id= :companyId")
//    List<UserDTO> findUsersByCompanyId(@Param("companyId") int companyId);
//
//    @Query("SELECT new com.stav.server.dto.UserDTO (u.userName, u.userType, c.name) " +
//            "FROM UserEntity u JOIN CompanyEntity c on u.company.id = c.id")
//    List<UserDTO> findUsersByPage(Pageable pageNumber);
//
    @Query("SELECT id FROM UserEntity")
    List<Long> findAllUsersIds();


    /************ Methods from Avi - very important !!!
     *
    @Query("select u from users u where user_name =:userName and password = :password")
    public SuccessfulLoginData login(@Param("userName") String userName, @Param("password") String password);

    @Query("select u from users u where user_name =:userName and password = :password")
    public SuccessfulLoginData login(@Param("userName") String userName, @Param("password") String password);

    @Query("select u from users u where user_name =:userName")
    public UserEntity isUserExist(@Param("userName") String userName);

    ******************/
}
