package com.stav.server.controllers;

import com.stav.server.dto.UserDTO;
import com.stav.server.entities.UserEntity;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.logic.UsersLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersLogic usersLogic;

    @Autowired
    public UsersController(UsersLogic usersLogic) {
        this.usersLogic = usersLogic;
    }

    @PostMapping
    public void createUser(@RequestBody UserEntity user) throws ServerException {
        this.usersLogic.createUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody UserEntity user) throws ServerException {
        this.usersLogic.updateUser(user);
    }

    @GetMapping("{userId}")
    public UserEntity getUser(@PathVariable("userId") long id) throws ServerException {
        UserEntity user = usersLogic.getUser(id);
        return user;
    }


    @GetMapping("/byCompanyId")
    public List<UserDTO> getUsersByCompanyId(@RequestParam("companyId") long companyId) throws ServerException {
        return usersLogic.getUsersByCompanyId(companyId);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") int id) throws ServerException{
        this.usersLogic.deleteUser(id, UserType.Admin);
    }
}
