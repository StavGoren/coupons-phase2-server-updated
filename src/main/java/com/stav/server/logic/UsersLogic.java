package com.stav.server.logic;

import com.stav.server.dal.ICompaniesDal;
import com.stav.server.dal.IUsersDal;
import com.stav.server.dto.UserDTO;
import com.stav.server.entities.UserEntity;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.ValidationsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UsersLogic {
    private IUsersDal iUsersDal;
//    private ICompaniesDal iCompaniesDal;
    private CompaniesLogic companiesLogic;

    @Autowired
    public UsersLogic(IUsersDal iUsersDal, CompaniesLogic companiesLogic) {
        this.iUsersDal = iUsersDal;
        this.companiesLogic = companiesLogic;
    }


    public long createUser(UserEntity user) throws ServerException {
        System.out.println("User name: " + user.getUserName());
        validateUser(user);
        iUsersDal.save(user);
        return user.getId();
    }

    public void updateUser(UserEntity user) throws ServerException {
        validateUser(user);
        iUsersDal.save(user);
    }

    public UserEntity getUser(long id) throws ServerException {
//        isValidIdId(id);
        return iUsersDal.findUser(id);
    }

    public List<UserDTO> getUsersByCompanyId(long companyId) throws ServerException {
        companiesLogic.isValidId(companyId);
        return iUsersDal.findUsersByCompanyId(companyId);
    }

    public void deleteUser(long id, UserType userType) throws ServerException {
        isValidIdId(id);
        iUsersDal.deleteById(id);
    }




     /****************************************
      * Avi's function - keep!!

    public String login(UserEntity user) throws Exception {
        // mocking a successful login
        SuccessfulLoginData userData = usersDal.login(user.getName(), user.getPassword());
        if (userData == null) {
            throw new Exception("Failed to login");
        }

        String token = JWTUtils.createJWT(userData);
        return token;
    }

      ******************************************/


    /*********************
     * ValidationsUtils
     ********************/

     protected void validateUser(UserEntity user) throws ServerException {
         isValidUserName(user.getUserName());
         validatePassword(user.getPassword());
     }

    private void validatePassword(String password) throws ServerException {
        if(password.length() < 8) {
            throw new ServerException(ErrorType.INVALID_PASSWORD_LENGTH, " for your own security, make sure your password is long enough");
        }
        if(password.length() > 45) {
            throw new ServerException(ErrorType.INVALID_PASSWORD_LENGTH, " please make sure your password meets the length requirements");
        }
    }

    private boolean isValidUserName(String userName) throws ServerException{
        validateUserName(userName);
//        List<UserEntity> userNameList = iUsersDal.findAllUsersIncludingIds();
//        for (int i = 0; i < userNameList.size(); i++) {
//            if(userNameList.get(i).getUserName().equals(userName)){
//                throw new ServerException(ErrorType.USERNAME_EXISTS, " please enter a different email");
//            }
//        }
        return true;
    }

    private static void validateUserName(String email) throws ServerException {
        System.out.println(email);
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ServerException(ErrorType.INVALID_EMAIL_PATTERN, " please check your entry");
        }
    }

    // For update, delete and get
    private boolean isValidIdId(long id) throws ServerException {
//        ValidationsUtils.validateNumber((int) id);

        List<Long> usersIdList = iUsersDal.findAllUsersIds();
        for(long userId : usersIdList) {
            if(userId == id) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " this user ID does not exist in our data base");
    }
}
